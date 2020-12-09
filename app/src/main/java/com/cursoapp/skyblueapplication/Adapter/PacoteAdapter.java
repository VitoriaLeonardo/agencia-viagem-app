package com.cursoapp.skyblueapplication.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoapp.skyblueapplication.Classes.Pacote;
import com.cursoapp.skyblueapplication.InfoPacoteFragment;
import com.cursoapp.skyblueapplication.R;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class PacoteAdapter extends RecyclerView.Adapter<PacoteAdapter.MyViewHolder> {

    private List<Pacote> pacotes;
    private FragmentActivity activityAtual;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView txtNome;
        TextView txtDescri;
        TextView txtValor;
        ImageView imageView;
        Button btnVerPac;
        ImageView imageView2;

        public MyViewHolder(View view){
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_pacote);
            txtNome = (TextView) view.findViewById(R.id.txt_nome_pacote);
            txtDescri = (TextView) view.findViewById(R.id.txt_descricao_pacote);
            txtValor = (TextView) view.findViewById(R.id.txt_valor_pacote);
            btnVerPac = (Button) view.findViewById(R.id.btn_ver_pacote);
            imageView = (ImageView) view.findViewById(R.id.imageViewH);
            imageView2 = (ImageView) view.findViewById(R.id.imageViewInfo);
        }
    }

    public PacoteAdapter(List<Pacote> pacotes, FragmentActivity activityAtual) {
        this.activityAtual = activityAtual;
        this.pacotes = pacotes;
    }

    @Override
    public PacoteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pacotes, parent, false);
        MyViewHolder mvh = new MyViewHolder(view);
        return mvh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.txtNome.setText(pacotes.get(position).nome);
        holder.txtDescri.setText(pacotes.get(position).descricao);
        holder.txtValor.setText(String.valueOf(pacotes.get(position).valorUnitario));

        final String imgURL  = pacotes.get(position).imagem;


        new DownLoadImageTask(holder.imageView).execute(imgURL);

        holder.btnVerPac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoPacoteFragment infoFrag = new InfoPacoteFragment(pacotes.get(holder.getAdapterPosition()).id);
                infoFrag.pacote = pacotes.get(holder.getAdapterPosition());
                openFragment(infoFrag);
                Toast.makeText(v.getContext(), "ID_PACOTE: " + pacotes.get(holder.getAdapterPosition()).id, Toast.LENGTH_LONG).show();
            }
        });

    }
    //Abrindo Fragment
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = activityAtual.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return pacotes.size();
    }



    //Imagem
    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        ImageView imageView2;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
            this.imageView2 = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
            imageView2.setImageBitmap(result);

        }
    }
}


