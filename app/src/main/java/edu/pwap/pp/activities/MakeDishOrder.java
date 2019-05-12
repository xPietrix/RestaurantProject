package edu.pwap.pp.activities;

import android.content.Context;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
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
import edu.pwap.pp.clients.DishApi;
import edu.pwap.pp.clients.DishCategoryApi;
import edu.pwap.pp.clients.OrderApi;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.models.Order;
import edu.pwap.pp.network.RetrofitInitializer;
import edu.pwap.pp.repositories.OrderRepository;
import edu.pwap.pp.services.OrderService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MakeDishOrder extends AppCompatActivity
{
    private static final String TAG = "MakeDishOrder.java";
    private CompositeDisposable disposable = new CompositeDisposable();
    private DishesAdapter dishesAdapter;
    private static List<Dish> dishesList = new ArrayList<>();
    private Button buttonShowDropDown;
    private String popUpContents[];
    private PopupWindow popupWindowCategories;
    private static List <String> categoriesList = new ArrayList<String>();
    private AdapterView.OnItemClickListener onItemClickListener;
    private EditText eTTableNumber;

    @BindView(R.id.linear_layout)
    public LinearLayout linearLayout;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.txt_empty_dishes_view)
    public TextView noDishesView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_make_dish_order);
        ButterKnife.bind(this);

        eTTableNumber = findViewById(R.id.editTextTableNumber);
        buttonShowDropDown = findViewById(R.id.dishCategoryChooseButton);
        OnClickListener handler = getButtonOnClickListener();
        buttonShowDropDown.setOnClickListener(handler);
        dishesAdapter = new DishesAdapter(this, dishesList);
        configureRecycleView();
        addOnItemTouchListenerToRecycleView();
        onItemClickListener = getOnItemClickListener();
        getAllDishCategories();
    }

    private void configureRecycleView()
    {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(dishesAdapter);
    }

    private void getAllDishCategories()
    {
        DishCategoryApi dishCategoryApi = RetrofitInitializer.getClient().create(DishCategoryApi.class);
        disposable.add(dishCategoryApi.getAllDishCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DishCategory>>() {
                    @Override
                    public void onSuccess(List<DishCategory> dishCategories) {
                        categoriesList.clear();

                        int i = 1;
                        for(DishCategory dishCategory: dishCategories)
                        {
                            categoriesList.add(dishCategory.getCategoryName() + "::" + i);
                            i++;
                        }
                        popUpContents = new String[categoriesList.size()];
                        categoriesList.toArray(popUpContents);

                        popupWindowCategories = popupWindowCategories();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                        showError(e);
                    }
                })
        );
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

    private OnClickListener getButtonOnClickListener()
    {
        return new OnClickListener()
        {
            public void onClick(View v)
            {
                if(v.getId() == R.id.dishCategoryChooseButton)
                {
                    popupWindowCategories.showAsDropDown(v, -10, 0);
                }
            }
        };
    }

    private OnItemClickListener getOnItemClickListener()
    {
        return new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3)
            {
                Context mContext = v.getContext();
                MakeDishOrder makeDishOrderActivity = ((MakeDishOrder) mContext);
                Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
                fadeInAnimation.setDuration(10);
                v.startAnimation(fadeInAnimation);
                makeDishOrderActivity.popupWindowCategories().dismiss();
                String selectedItemText = ((TextView) v).getText().toString();
                makeDishOrderActivity.buttonShowDropDown.setText(selectedItemText);
                String selectedItemTag = v.getTag().toString();
                Toast.makeText(mContext, "Dish Category ID is: " + selectedItemTag, Toast.LENGTH_SHORT).show();

                getAllDishesWithCategory(selectedItemTag);
            }
        };
    }

    private PopupWindow popupWindowCategories()
    {
        PopupWindow popupWindow = new PopupWindow(this);
        ListView listViewCategories = new ListView(this);
        listViewCategories.setAdapter(categoriesAdapter(popUpContents));
        listViewCategories.setOnItemClickListener(onItemClickListener);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(400);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(listViewCategories);

        return popupWindow;
    }

    private ArrayAdapter<String> categoriesAdapter(String dogsArray[])
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dogsArray) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                String item = getItem(position);
                String[] itemArr = item.split("::");
                String text = itemArr[0];
                String id = itemArr[1];

                TextView listItem = new TextView(MakeDishOrder.this);
                listItem.setText(text);
                listItem.setTag(id);
                listItem.setTextSize(22);
                listItem.setPadding(10, 10, 10, 10);
                listItem.setTextColor(Color.WHITE);

                return listItem;
            }
        };

        return adapter;
    }

    public void getAllDishesWithCategory(String id)
    {
        DishApi apiService = RetrofitInitializer.getClient().create(DishApi.class);
        disposable.add(
                apiService.getDishesWithCategory(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Dish>>() {
                            @Override
                            public void onSuccess(List<Dish> dishes) {
                                dishesList.clear();
                                dishesList.addAll(dishes);
                                dishesAdapter.notifyDataSetChanged();

                                toggleEmptyNotes();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getMessage());
                                showError(e);
                            }
                        })
        );
    }

    private void orderDish(Dish dish)
    {
        String date = "2019-12-10T11:21:37.999";
        List<Dish> dishesToOrder = new ArrayList<>();
        dishesToOrder.add(dish);
        int tableNumber = Integer.parseInt(eTTableNumber.getText().toString());
        Order order = new Order(0, dish.getDishPrice(), tableNumber, date,
                dish.getEstimatedPreparationTime(), dishesToOrder);

        OrderApi api = RetrofitInitializer.getClient().create(OrderApi.class);
        OrderService orderService = new OrderService(new OrderRepository());
        orderService.addOrder(order, api);

        Toast.makeText(MakeDishOrder.this,"Order made " + "(" + dish.getDishName() + " "
                + dish.getDishPrice() + "zł )", Toast.LENGTH_SHORT).show();
    }

    private void showActionsDialog(final int position)
    {
        CharSequence options[] = new CharSequence[]{"YES", "NO"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to order that dish?");
        builder.setItems(options, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if (which == 0)
                {
                    orderDish(dishesList.get(position));
                }
            }
        });
        builder.show();
    }

    private void toggleEmptyNotes()
    {
        if (dishesList.size() > 0)
        {
            noDishesView.setVisibility(View.GONE);
        }
        else
            {
              noDishesView.setVisibility(View.VISIBLE);
            }
    }

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
}
