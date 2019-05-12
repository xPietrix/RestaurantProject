package edu.pwap.pp.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import edu.pwap.pp.R;
import edu.pwap.pp.activities.listeners.DishCategorySelectedListener;
import edu.pwap.pp.activities.utils.StringProvider;
import edu.pwap.pp.clients.DishApi;
import edu.pwap.pp.clients.DishCategoryApi;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.network.RetrofitInitializer;
import edu.pwap.pp.repositories.DishRepository;
import edu.pwap.pp.services.DishService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GetAllDishesActivity extends AppCompatActivity
{
    private static final String TAG = "CategoriesActivity.java";
    private static TextView textViewAllDishesList;
    private DishService dishService;
    private List<Dish> dishList;

    private Button buttonShowDropDown;
    private String popUpContents[];
    private PopupWindow popupWindowCategories;
    private AdapterView.OnItemClickListener onItemClickListener;
    private static List <String> categoriesList = new ArrayList<String>();
    private CompositeDisposable disposable = new CompositeDisposable();

    @BindView(R.id.linear_layout)
    public LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_dishes);

        textViewAllDishesList = findViewById(R.id.textViewAllDishesList);
        buttonShowDropDown = findViewById(R.id.getDishesDishCategoryChooseButton);
        OnClickListener handler = getButtonOnClickListener();
        buttonShowDropDown.setOnClickListener(handler);
        onItemClickListener = getOnItemClickListener();

        getAllDishCategories();
    }

    private OnClickListener getButtonOnClickListener()
    {
        return new OnClickListener()
        {
            public void onClick(View v)
            {
                if(v.getId() == R.id.getDishesDishCategoryChooseButton)
                {
                    popupWindowCategories.showAsDropDown(v, -10, 0);
                }
            }
        };
    }

    private AdapterView.OnItemClickListener getOnItemClickListener()
    {
        return new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View v, int arg2, long arg3)
            {
                Context mContext = v.getContext();
                GetAllDishesActivity getDishesActivity = ((GetAllDishesActivity) mContext);
                Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
                fadeInAnimation.setDuration(10);
                v.startAnimation(fadeInAnimation);
                getDishesActivity.popupWindowCategories().dismiss();
                String selectedItemText = ((TextView) v).getText().toString();
                getDishesActivity.buttonShowDropDown.setText(selectedItemText);
                String selectedItemTag = v.getTag().toString();
                Toast.makeText(mContext, "Dish Category ID is: " + selectedItemTag, Toast.LENGTH_SHORT).show();

                if(selectedItemTag != null)
                {
                    getAllDishesWithCategory(selectedItemTag);
                }
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

    private ArrayAdapter<String> categoriesAdapter(String categories[])
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                String item = getItem(position);
                String[] itemArr = item.split("::");
                String text = itemArr[0];
                String id = itemArr[1];

                TextView listItem = new TextView(GetAllDishesActivity.this);
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

    private void getAllDishesWithCategory(String dishCategoryId)
    {
        DishApi api = RetrofitInitializer.getClient().create(DishApi.class);
        dishService = new DishService(new DishRepository());
        dishList = dishService.getDishesWithCategory(dishCategoryId, api);
    }

    public static void changeTextView(List<Dish> list)
    {
        textViewAllDishesList.setText(StringProvider.dishesWithCategoryString(list));
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
