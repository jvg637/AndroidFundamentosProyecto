package es.maps.programacion.fundamentos.androidfundamentosproyecto.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.caverock.androidsvg.SVG;

import java.util.Vector;

import es.maps.programacion.fundamentos.androidfundamentosproyecto.R;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.application.MapsApplication;
import es.maps.programacion.fundamentos.androidfundamentosproyecto.sqlite.pojo.Pais;

/**
 * Created by jvg63 on 09/12/2016.
 */

public class AdaptadorPaises {
    /**
     * Created by jvg63 on 01/10/2016.
     */
    public static class PaisesAdapter extends RecyclerView.Adapter<PaisesAdapter.ViewHolder> {
        private LayoutInflater inflador;
        private Vector<Pais> lista;
        protected View.OnClickListener onClickListener;
        private Context context;
        private boolean isSpanish=false;

        public void setOnItemClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public PaisesAdapter(Context context, Vector<Pais> lista) {
            isSpanish=(((MapsApplication) context.getApplicationContext()).esIdiomaEspanyol());
            isSpanish=(((MapsApplication) context.getApplicationContext()).esIdiomaEspanyol());
            this.lista = lista;

            this.context = context;
            inflador = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void asignaImagenSVG(String imagen, ImageView bandera) {
            // Load and parse a SVG
            SVG svg = null;
            Drawable drawable = null;
            try {

                svg = SVG.getFromAsset(context.getAssets(), "flags/" + imagen);
                drawable = new PictureDrawable(svg.renderToPicture());
            } catch (Exception e) {
                //e.printStackTrace();
            }

            if (drawable != null)
                bandera.setImageDrawable(drawable);
            else {
                //System.out.println("NO_ICONO:" + imagen);
                bandera.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.no_icon));
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v;

            if (viewType == 0)
                v = inflador.inflate(R.layout.elemento_impar, parent, false);
            else
                v = inflador.inflate(R.layout.elemento_par, parent, false);

            v.setOnClickListener(onClickListener);
            return new ViewHolder(v);

        }

        @Override
        public int getItemViewType(int position) {
            // Just as an example, return 0 or 2 depending on position
            // Note that unlike in ListView adapters, types don't have to be contiguous
            return position % 2 * 2;
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int i) {
            Pais elem = lista.get(i);

            //holder.id_pais.setText(elem.getIdPais() );
            holder.pais_es.setText((isSpanish)?elem.getPaisES():elem.getPaisEN());
            //holder.pais_en.setText(elem.getPaisEN() );
            holder.divisas.setText(elem.getDivisas());
            holder.himno.setChecked((elem.getHimno() != null && !elem.getHimno().isEmpty()));

            // SVG
            //asignaImagen(elem.getIcono(), holder.icono);


            Glide
                    .with(context)
                    .load("file:///android_asset/flags/" + elem.getIdPais2() + ".png")

                    .placeholder(R.drawable.pause)
                    .error(R.drawable.no_icon)
                    .crossFade()
                    .into(holder.icono);


        }

        @Override
        public int getItemCount() {
            return lista.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView icono;
            public TextView pais_es, pais_en, divisas, id_pais;
            public CheckBox himno;


            ViewHolder(View itemView) {
                super(itemView);
                icono = (ImageView) itemView.findViewById(R.id.icono);
                icono.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                pais_es = (TextView) itemView.findViewById(R.id.pais_es);
                //pais_en = (TextView) itemView.findViewById(R.id.pais_en);
                divisas = (TextView) itemView.findViewById(R.id.divisas);
                himno = (CheckBox) itemView.findViewById(R.id.himno);
                //id_pais = (TextView) itemView.findViewById(R.id.id_pais);
            }
        }

    }
}
