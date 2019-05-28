package edu.pwap.pp.activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.pwap.pp.R;
import edu.pwap.pp.activities.utils.MyDividerItemDecoration;
import edu.pwap.pp.activities.utils.RecyclerTouchListener;
import edu.pwap.pp.clients.OrderApi;
import edu.pwap.pp.models.Order;
import edu.pwap.pp.network.RetrofitInitializer;
import edu.pwap.pp.repositories.OrderRepository;
import edu.pwap.pp.services.OrderService;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ChefOrdersActivity extends AppCompatActivity
{
    private static final String TAG = "ChOrdersToPrepare.java";
    private CompositeDisposable disposable = new CompositeDisposable();
    private OrdersToPrepareAdapter ordersAdapter;
    private static List<Order> ordersList = new ArrayList<>();

    @BindView(R.id.chef_orders_linear_layout)
    public LinearLayout linearLayout;

    @BindView(R.id.prepared_orders_recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.txt_empty_orders_to_prepare_view)
    public TextView noOrdersToPrepareView;

    private Button buttonRefreshOrderToPrepare;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_orders);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ButterKnife.bind(this);

        buttonRefreshOrderToPrepare = findViewById(R.id.buttonRefreshOrderToPrepareList);
        setRefreshButtonListener();

        ordersAdapter = new OrdersToPrepareAdapter(this, ordersList);
        configureRecycleView();
        addOnItemTouchListenerToRecycleView();

        getAllOrdersToPrepare();
    }

    private void setRefreshButtonListener()
    {
        buttonRefreshOrderToPrepare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getAllOrdersToPrepare();
            }
        });
    }

    private void configureRecycleView()
    {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 30));
        recyclerView.setAdapter(ordersAdapter);
    }

    private void addOnItemTouchListenerToRecycleView()
    {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener()
        {
            @Override
            public void onClick(View view, final int position)
            {
            }

            @Override
            public void onLongClick(View view, int position)
            {
                showActionsDialog(position);
            }
        }));
    }

    private void showActionsDialog(final int position)
    {
        CharSequence options[] = new CharSequence[]{"YES", "NO"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to prepare that order?");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if (which == 0)
                {
                    prepareOrder(ordersList.get(position)).subscribe(new DisposableSingleObserver<Order>()
                    {
                        @Override
                        public void onSuccess(Order order)
                        {

                        }

                        @Override
                        public void onError(Throwable e)
                        {
                            Log.d("ERROR", "ERROR WITH PREPARE ORDER METHOD!");
                        }
                    });

                    ordersList.remove(position);
                    ordersAdapter.notifyItemRemoved(position);
                    getAllOrdersToPrepare();
                    Log.e("ROZMIAR TEST", "ROZMIAR LISTY PO UPDATCIE: " + ordersList.size());
                    ordersAdapter.setData(ordersList);
                }
            }
        });
        builder.show();
    }

    private void getAllOrdersToPrepare()
    {
        OrderApi apiService = RetrofitInitializer.getClient().create(OrderApi.class);
        disposable.add(
                apiService.getOrdersToPrepare()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Order>>()
                        {
                            @Override
                            public void onSuccess(List<Order> orders) {
                                Log.e("ROZMIAR", "ROZMIAR LISTY KTÓRA PRZYSZŁA: " + orders.size());
                                ordersList.clear();
                                ordersList.addAll(orders);
                                //ordersList = new ArrayList<>(orders);
                                Log.e("ROZMIAR", "ROZMIAR LISTY: " + ordersList.size());
                                ordersAdapter.notifyDataSetChanged();

                                toggleEmptyOrders();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                                showError(e);
                            }
                        })
        );
    }

    private Single<Order> prepareOrder(Order order)
    {
        OrderApi api = RetrofitInitializer.getClient().create(OrderApi.class);
        OrderService orderService = new OrderService(new OrderRepository());
        Single<Order> single = orderService.prepareOrder(order.getId(), api);

        Toast.makeText(ChefOrdersActivity.this,"Order " + order.getId()
                + " has been prepared ", Toast.LENGTH_SHORT).show();

        return single;
    }

    private void toggleEmptyOrders()
    {
        if (ordersList.size() > 0)
        {
            noOrdersToPrepareView.setVisibility(View.GONE);
        }
        else
        {
            noOrdersToPrepareView.setVisibility(View.VISIBLE);
        }
    }

    //region showError

    private void showError(Throwable e)
    {
        String message = "";
        try
        {
            if (e instanceof IOException)
            {
                message = "No internet connection!";
            } else if (e instanceof HttpException)
            {
                HttpException error = (HttpException) e;
                String errorBody = error.response().errorBody().string();
                JSONObject jObj = new JSONObject(errorBody);

                message = jObj.getString("error");
            }
        } catch (IOException e1)
        {
            e1.printStackTrace();
        } catch (JSONException e1)
        {
            e1.printStackTrace();
        } catch (Exception e1)
        {
            e1.printStackTrace();
        }

        if (TextUtils.isEmpty(message))
        {
            message = "Unknown error occurred! Check LogCat.";
        }

        Snackbar snackbar = Snackbar
                .make(linearLayout, message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    //endregion error
}
