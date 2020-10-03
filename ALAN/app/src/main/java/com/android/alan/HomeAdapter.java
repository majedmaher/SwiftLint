package com.android.alan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    //
    private ArrayList<Details> detailsArrayList;
    private RequestQueue requestQueue;

    //
    public HomeAdapter(ArrayList<Details> detailsArrayList, RequestQueue requestQueue) {
        //
        this.detailsArrayList = detailsArrayList;
        this.requestQueue = requestQueue;
    }

    //
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //
        return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home, parent, false));
    }

    //
    @Override
    public void onBindViewHolder(@NonNull final HomeViewHolder holder, final int position) {
        //
        holder.textView_home_title.setText(detailsArrayList.get(position).getTitle());
        ImageRequest imageRequest = new ImageRequest(detailsArrayList.get(position).getImage(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(final Bitmap response) {
                //
                holder.imageView_home_image.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        });
        requestQueue.add(imageRequest);
        holder.textView_home_details.setText(detailsArrayList.get(position).getDetails());
        holder.button_home_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                view.getContext().startActivity(new Intent(view.getContext(), DetailsActivity.class).putExtra("Title", detailsArrayList.get(position).getTitle()).putExtra("Image", detailsArrayList.get(position).getImage()).putExtra("Details", detailsArrayList.get(position).getDetails()));
            }
        });
    }

    //
    @Override
    public int getItemCount() {
        //
        return detailsArrayList.size();
    }

    //
    public class HomeViewHolder extends RecyclerView.ViewHolder {

        //
        TextView textView_home_title, textView_home_details;
        ImageView imageView_home_image;
        Button button_home_details;

        //
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            //
            textView_home_title = itemView.findViewById(R.id.textView_home_title);
            imageView_home_image = itemView.findViewById(R.id.imageView_home_image);
            textView_home_details = itemView.findViewById(R.id.textView_home_details);
            button_home_details = itemView.findViewById(R.id.button_home_details);
        }
    }
}
