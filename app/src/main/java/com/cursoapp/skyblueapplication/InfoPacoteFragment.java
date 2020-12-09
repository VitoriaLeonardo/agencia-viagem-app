package com.cursoapp.skyblueapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cursoapp.skyblueapplication.Classes.Destino;
import com.cursoapp.skyblueapplication.Classes.MeuIP;
import com.cursoapp.skyblueapplication.Classes.Origem;
import com.cursoapp.skyblueapplication.Classes.Pacote;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class InfoPacoteFragment extends Fragment {
    public NumberPicker numberPicker;
    public NumberPicker numberPicker2;
    public NumberPicker numberPicker3;
    public Button btnComprar;
    public Pacote pacote = new Pacote();
    private RequestQueue mQueue;

    private ImageView imageView;

    private TextView txtNomePacote;
    private LinearLayout linearLayoutInfo;
    private LinearLayout linearLayoutLoading;

    private TextView txtDescricaoPacote;
    private TextView txtInfoDestPacote;
    private TextView txtInfoOrigPacote;
    private TextView txtDataPacote;
    private TextView txtTranspPacote;
    private TextView txtValorPacote;

    public InfoPacoteFragment(View item){
        //super(item);
        // Required empty public constructor
    }

    public int id;
    public InfoPacoteFragment(int id) {
        this.id = id;
    }

    public static InfoPacoteFragment newInstance(int id) {
        InfoPacoteFragment fragment = new InfoPacoteFragment(id);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        esconderActionBar(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_info_pacote, container, false);

        btnComprar = (Button) view.findViewById(R.id.btn_comprar_pacote);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment pagamento = new PagamentoFragment();
                openFragment(pagamento);
            }
        });

        //return inflater.inflate(R.layout.fragment_info_pacote, container, false);
        return view;

    }

    public static void esconderActionBar(Activity parent) {
        MainActivity mainActivity = (MainActivity) parent;
        mainActivity.getSupportActionBar().hide();
    }

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        txtNomePacote = (TextView) view.findViewById(R.id.txt_nome_pacote);
        txtDescricaoPacote = (TextView) view.findViewById(R.id.txt_descricao_pacote);
        txtInfoDestPacote = (TextView) view.findViewById(R.id.txt_info_dest_pacote);
        txtInfoOrigPacote = (TextView) view.findViewById(R.id.txt_info_orig_pacote);
        txtDataPacote = (TextView) view.findViewById(R.id.txt_data_pacote);
        txtTranspPacote = (TextView) view.findViewById(R.id.txt_transp_pacote);
        txtValorPacote = (TextView) view.findViewById(R.id.txt_valor_pacote);
        imageView = (ImageView) view.findViewById(R.id.imageViewInfo);
        linearLayoutInfo = (LinearLayout) view.findViewById(R.id.layoutInfo);
        linearLayoutLoading = (LinearLayout) view.findViewById(R.id.layoutLoading);

        linearLayoutInfo.setVisibility(view.GONE);
        linearLayoutLoading.setVisibility(view.VISIBLE);


        //Get
        String url = "http://"+ MeuIP.ip +"/api/getPacote.php?id="+id;

        //Para setar
        mQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            pacote.id = response.getInt("id");
                            pacote.valorUnitario = response.getInt("valorUnitario");
                            pacote.nome = response.getString("nome");
                            pacote.tipoTransporte = response.getString("tipoTransporte");
                            pacote.descricao = response.getString("descricaoPacote");
                            pacote.dataIda = response.getString("dataIda");
                            pacote.dataVolta = response.getString("dataVolta");
                            pacote.imagem = response.getString("imagem");

                            JSONObject origemJson = response.getJSONObject("origem");
                            Origem origem = new Origem();
                            origem.id = origemJson.getInt("id");
                            origem.nome = origemJson.getString("nome");
                            origem.rua = origemJson.getString("rua");
                            origem.cep = origemJson.getString("cep");
                            origem.uf = origemJson.getString("uf");
                            origem.cidade = origemJson.getString("cidade");
                            origem.bairro = origemJson.getString("bairro");
                            pacote.origem = origem;

                            JSONObject destinoJson = response.getJSONObject("destino");
                            Destino destino = new Destino();
                            destino.id = destinoJson.getInt("id");
                            destino.nome = destinoJson.getString("nome");
                            destino.rua = destinoJson.getString("rua");
                            destino.cep = destinoJson.getString("cep");
                            destino.uf = destinoJson.getString("uf");
                            destino.cidade = destinoJson.getString("cidade");
                            destino.bairro = destinoJson.getString("bairro");
                            pacote.destino = destino;

                            setarPacote(view);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

        NumberPicker numberPickerAdult = (NumberPicker) getView().findViewById(R.id.numberpicker);
        numberPickerAdult.setMinValue(1);
        numberPickerAdult.setMaxValue(10);
        numberPickerAdult.setWrapSelectorWheel(true);
        numberPickerAdult.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {

            }
        });

        NumberPicker numberPickerCrianca = (NumberPicker) getView().findViewById(R.id.numberpicker2);
        numberPickerCrianca.setMinValue(0);
        numberPickerCrianca.setMaxValue(10);
        numberPickerCrianca.setWrapSelectorWheel(true);
        numberPickerCrianca.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
            }
        });


        NumberPicker numberPickerQuarto = (NumberPicker) getView().findViewById(R.id.numberpicker3);
        numberPickerQuarto.setMinValue(1);
        numberPickerQuarto.setMaxValue(4);
        numberPickerQuarto.setWrapSelectorWheel(true);
        numberPickerQuarto.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
            }
        });
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //Atribuindo
    public void setarPacote(View view){

        txtNomePacote.setText(pacote.nome);
        txtDescricaoPacote.setText(pacote.descricao);
        txtDataPacote.setText(pacote.dataIda);
        txtDataPacote.setText(pacote.dataVolta);
        txtInfoDestPacote.setText(pacote.destino.rua + ", " + pacote.destino.bairro + ", " + pacote.destino.cidade +
                "/" + pacote.destino.uf + " - " + pacote.destino.cep);
        txtInfoOrigPacote.setText(pacote.origem.rua + ", " + pacote.origem.bairro + ", " + pacote.origem.cidade +
                "/" + pacote.origem.uf + " - " + pacote.origem.cep);

        final String imgURL  = pacote.imagem;

        new DownLoadImageTask(imageView).execute(imgURL);
        linearLayoutInfo.setVisibility(view.VISIBLE);
        linearLayoutLoading.setVisibility(view.GONE);
    }


    //Imagem
    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
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
