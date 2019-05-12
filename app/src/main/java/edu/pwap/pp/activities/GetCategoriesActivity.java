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
import edu.pwap.pp.activities.utils.StringProvider;
import edu.pwap.pp.clients.DishCategoryApi;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.network.RetrofitInitializer;
import edu.pwap.pp.repositories.DishCategoryRepository;
import edu.pwap.pp.services.DishCategoryService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GetCategoriesActivity extends AppCompatActivity
{
    private static TextView textViewAllDishCategories;
    private DishCategoryService dishCategoryService;
    private List<DishCategory> dishCategoriesList;

    @BindView(R.id.linear_layout)
    public LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_dish_categories);
        textViewAllDishCategories = findViewById(R.id.textViewDishCategoriesList);

        getAllDishCategories();
    }

    private void getAllDishCategories()
    {
        DishCategoryApi service = RetrofitInitializer.getClient().create(DishCategoryApi.class);
        dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        dishCategoriesList = dishCategoryService.getAllDishCategories(service);
    }

    public static void changeTextView(List<DishCategory> list)
    {
        textViewAllDishCategories.append(StringProvider.allDishCategoriesString(list));
    }
}
