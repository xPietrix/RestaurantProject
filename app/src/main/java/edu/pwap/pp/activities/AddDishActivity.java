package edu.pwap.pp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.pwap.pp.R;
import edu.pwap.pp.dataGetters.GetEverything;
import edu.pwap.pp.models.Dish;
import edu.pwap.pp.models.DishCategory;
import edu.pwap.pp.repositories.DishCategoryRepository;
import edu.pwap.pp.repositories.DishRepository;
import edu.pwap.pp.services.DishCategoryService;
import edu.pwap.pp.services.DishService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDishActivity extends AppCompatActivity
{
    private Button addDishToDBButton;
    private TextView dishNameTV;
    private EditText dishNameText;
    private EditText dishPriceText;
    private EditText estimatedPrepTimeText;
    private EditText dishCategoryIdText;
    private TextView textViewAddingDish;

    private String content;
    private DishCategory category;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        textViewAddingDish = findViewById(R.id.textViewAddingDish);
        addDishToDBButton = findViewById(R.id.buttonAddDishToDB);
        dishNameTV = findViewById(R.id.textViewDishName);
        dishNameText = findViewById(R.id.editTextDishName);
        dishPriceText = findViewById(R.id.editTextDishPrice);
        estimatedPrepTimeText = findViewById(R.id.editTextEstimatedPrepTime);
        dishCategoryIdText = findViewById(R.id.editTextDishCategoryId);

        //setAddDishButtonListener();
        getDishCategoryWithId(3);
        //textViewAddingDish.append(getCategory().toString());
    }

    public void addDish(Dish dish)
    {
        DishService dishService = new DishService(new DishRepository());
        dishService.addDish(dish);
    }

    public void setAddDishButtonListener()
    {
        addDishToDBButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = dishNameText.getText().toString();
                double price = Double.parseDouble(dishPriceText.getText().toString());
                long estimatedPrepTime = Long.parseLong(estimatedPrepTimeText.getText().toString());
                //long categoryId = categoryIdText.getText();

                long categoryId = Long.parseLong(dishCategoryIdText.getText().toString());
                //getDishCategoryWithId(categoryId);
                //String categoryName = this.category.getCategoryName();
                //String categoryName = "Obiady";
                //DishCategory dishCategory = getDishCategory(categoryId);
                //dishCategory.setId(categoryId);
                //DishCategory dishCategory = new DishCategory(categoryId, categoryName);
               // Dish dish = new Dish(name, price, estimatedPrepTime, dishCategory);
                //addDish(dish);

                //getDishCategoryWithId(3);

                Toast.makeText(v.getContext(),"Dish added to database", Toast.LENGTH_SHORT).show();
                dishNameText.setText("");
            }
        });
    }

    /*public DishCategory getDishCategory(long id)
    {
        DishCategoryService dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        return dishCategoryService.getDishCategory(id);
    }*/

    public void getDishCategoryWithId(long id)
    {
        //getEverything = new GetEverything("http:/192.168.1.101:8080/");
        //getEverything = new GetEverything("http:/192.168.43.79:8080/");
        DishCategoryService dishCategoryService = new DishCategoryService(new DishCategoryRepository());
        Call<DishCategory> call = dishCategoryService.getDishCategory(id);
        call.enqueue(new Callback<DishCategory>()
        {
            @Override
            public void onResponse(Call<DishCategory> call, Response<DishCategory> response)
            {
                if(!response.isSuccessful())
                {
                    textViewAddingDish.setText("Code: " + response.code());
                    return;
                }

                DishCategory category = response.body();

                category = new DishCategory(category.getId(), category.getCategoryName());
                setCategory(category);
            }

            @Override
            public void onFailure(Call<DishCategory> call, Throwable t)
            {
                textViewAddingDish.setText(t.getMessage());
            }
        });
    }

    public String getTextView()
    {
        return this.content;
    }

    public void setTextView(String content)
    {
        this.content = content;
    }

    public void appendTextView(String content)
    {
        textViewAddingDish.append(content);
    }

    public void setCategory(DishCategory category)
    {
        this.category = category;
        String content="";

        content = content + "\n";
        content = content + "ID: " + this.category.getId() + "\n";
        content = content + "Category name: " + this.category.getCategoryName() + "\n";

        //setTextView(content);
        textViewAddingDish.append(content);
    }

    public DishCategory getCategory()
    {
        return this.category;
    }
}
