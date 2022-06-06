package com.example.pruebaimbox;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebaimbox.ui.main.PageViewModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final String[] datosPerros;
    private List<String> fotosPerros;
    static TextView itemText;
    static ImageView imageViewId;
    static private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View


                itemText= itemView.findViewById(R.id.textViewBreed);
                imageViewId= itemView.findViewById(R.id.imageView);
                context= view.getContext();

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String razaSelec =itemText.getText().toString();
                        MainActivity mainActivity= new MainActivity();
                        mainActivity.trimDatosJson2(razaSelec,context);


                    }
                });
            //textView = (TextView) view.findViewById(R.id.textViewBreed);
        }

        public TextView getTextView() { return itemText; }
        public ImageView getImageView(){return  imageViewId;}
    }


    public Adapter(String[] datos, List<String>fotos) {
        datosPerros = datos;
        fotosPerros= fotos;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mascotas_row, viewGroup, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(datosPerros[position]);
        Picasso.get().load(fotosPerros.get(position)).into(viewHolder.getImageView());
        String mensaje= datosPerros[position].toString();
        //String fotos= fotosPerros[position].toString();


       bindDatos(mensaje);
      // bindImg();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return datosPerros.length;
    }

    public static void bindDatos (String mensaje){
       // lblTitulo.text=cita._id.toString()
        itemText.setText(mensaje.toUpperCase(Locale.ROOT).replaceAll("[^\\w\\.@-]", ""));
    }
    public static void bindImg (){
        // lblTitulo.text=cita._id.toString()

        Picasso.get().load("https://dog.ceo/api/breeds/image/random").into(imageViewId);

    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }





}

