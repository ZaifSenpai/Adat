package zaifsenpai.adat;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zaifsenpai.adat.Retrofit.APIClient;
import zaifsenpai.adat.Retrofit.Response.RandomPhoto;

public class MainActivity extends AppCompatActivity {
    Adats adats = new Adats();
    AdatAdapter adapter;
    APIClient apiClient;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.unsplash.com/").addConverterFactory(GsonConverterFactory.create()).build();
        apiClient = retrofit.create(APIClient.class);

        loadData();
        initAddButton();
        initRecycler();
    }

    private void initRecycler() {
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new AdatAdapter(this, adats, new RecyclerMethods() {
            @Override
            public void delete(int position) {
                adats.getNames().remove(position);
                adats.getCount().remove(position);
                adats.getBackgrounds().remove(position);
                adapter.setAdats(adats);
                adapter.notifyDataSetChanged();
                saveData();
            }

            @Override
            public void add(int position) {
                adats.getCount().set(position, adats.getCount().get(position) + 1);
                adapter.setAdats(adats);
                adapter.notifyDataSetChanged();
                saveData();
            }

            @Override
            public void setRandomImage(ImageView background, int position) {
                String url = adats.getBackgrounds().size() >= position ? adats.getBackgrounds().get(position) : null;
                if (url != null) {
                    Glide.with(MainActivity.this)
                            .load(url)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(background);
                }
            }
        });
        recycler.setAdapter(adapter);
    }

    private void saveData() {
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        editor.putString("adats", gson.toJson(adats));
        editor.apply();
    }

    private void loadData() {
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String str = pref.getString("adats", null);
        if (str != null)
            adats = gson.fromJson(str, Adats.class);
    }

    private void initAddButton() {
        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                EditText newName = new EditText(MainActivity.this);
                newName.setHint("Enter name");
                newName.setInputType(InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_WORDS | InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE);
                builder.setTitle("New");
                builder.setMessage("Enter new habit name");
                builder.setView(newName);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addNewAdat(newName.getText().toString());
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    private void addNewAdat(String name) {
        Call<RandomPhoto> call = apiClient.GetRandomBackground("2cddd19510512be093ed765531d991fca468b47867e9cc42f4ff64f09aec087e", name);
        call.enqueue(new Callback<RandomPhoto>() {
            @Override
            public void onResponse(Call<RandomPhoto> call, Response<RandomPhoto> response) {
                if (response.code() == 200 && response.body() != null && response.body().getErrors() == null) {
                    adats.getBackgrounds().add(response.body().getUrls().getRegular());
                    adats.getNames().add(name);
                    adats.getCount().add(0);
                    adapter.setAdats(adats);
                    adapter.notifyDataSetChanged();
                    saveData();
                }
            }

            @Override
            public void onFailure(Call<RandomPhoto> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to add", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            saveData();
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to leave", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
