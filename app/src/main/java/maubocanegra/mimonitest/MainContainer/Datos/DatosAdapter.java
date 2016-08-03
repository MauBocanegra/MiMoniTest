package maubocanegra.mimonitest.MainContainer.Datos;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import maubocanegra.mimonitest.MainContainer.Busqueda.SearchActivity;
import maubocanegra.mimonitest.MainContainer.Detalle.DetalleActivity;
import maubocanegra.mimonitest.R;

/**
 * Created by Mauro on 29/07/2016.
 */
public class DatosAdapter extends RecyclerView.Adapter<DatosAdapter.ViewHolder>{

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String[] categories;
    private String[] imageUrls;

    public DatosAdapter(Context context){
        categories = context.getResources().getStringArray(R.array.datos_secciones);
        imageUrls = context.getResources().getStringArray(R.array.images_secciones);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleText;
        public LayoutInflater inflater;
        public ViewGroup parent;

        public ViewHolder(View v){
            super(v);
            titleText = (TextView)v.findViewById(R.id.titleList);
            inflater = LayoutInflater.from(v.getContext());
            parent = (ViewGroup) v.findViewById(R.id.container);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //rutina que especifica si es el header o es un item normal
        View view=null;
        if(viewType == TYPE_ITEM){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datos_recycler, parent, false);
        }else if(viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datos_first_recycler, parent, false);
        }
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==TYPE_HEADER)
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //Verificamos que no sea el header
        if(holder.titleText != null) {
            //seteamos el titulo
            holder.titleText.setText(categories[position-1]);
            //agregamos 5 vistas arbitrariamente
            for(int i=0; i<5; i++){
                final View childView = holder.inflater.inflate(R.layout.item_datos_subitem, holder.parent, false);
                if(holder.parent.getChildCount()>4){return;}
                ((TextView)childView.findViewById(R.id.imageCaption)).setText(categories[position-1]);
                holder.parent.addView(childView, holder.parent.getLayoutParams());
                //Bajamos la imagen de la categoria y la colocamos
                if(childView!=null) {
                    Picasso.with(childView.getContext())
                            .load(imageUrls[position-1])
                            //.memoryPolicy(MemoryPolicy.NO_STORE)
                            //.memoryPolicy(MemoryPolicy.NO_CACHE)
                            .into((ImageView) childView.findViewById(R.id.imageToLoad));

                    //a cada imagen le seteamos el clickListener para que abra el detalle
                    childView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(view.getContext(), DetalleActivity.class);
                            //Activamos la transicion si es post-lollipop
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), view, "");
                                Bundle bundle = options.toBundle();
                                bundle.putString("activityTitle",categories[position-1]);
                                view.getContext().startActivity(intent, options.toBundle());
                            }else{
                                view.getContext().startActivity(intent);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return categories.length+1;
    }

}
