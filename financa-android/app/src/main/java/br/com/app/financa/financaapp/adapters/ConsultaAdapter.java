package br.com.app.financa.financaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.app.financa.financaapp.R;
import br.com.app.financa.financaapp.bean.BeanAdapter;
import br.com.app.financa.financaapp.bean.IBean;

/**
 * Created by jccf-note on 02/06/2016.
 */
public class ConsultaAdapter extends BaseAdapter {
    private Context contexto;
    private ArrayList<?> lista;
    private int fragmentLayout;

    public ConsultaAdapter(Context contexto, ArrayList<?> lista, int fragmentLayout) {
        this.contexto = contexto;
        this.lista = lista;
        this.fragmentLayout = fragmentLayout;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeanAdapter objetoBean = ((IBean) getItem(position)).getBeanAdapterConsulta();

        LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
        //View view = inflater.inflate(R.layout.fragment_usuario_consulta, null);
        View view = inflater.inflate(fragmentLayout, null);

        TextView titulo = (TextView) view.findViewById(R.id.tvTitulo);
        titulo.setText(objetoBean.getTitulo());

        TextView subtitulo = (TextView) view.findViewById(R.id.tvSubTitulo);
        subtitulo.setText(objetoBean.getSubTitulo());

        TextView valor = (TextView) view.findViewById(R.id.tvValor);
        valor.setText(objetoBean.getValor());

        //ImageView imagem = (ImageView) view.findViewById(R.id.imgConsulta);
        //imagem.setImageResource(R.drawable.ic_android_black_24px);

        return view;
    }
}
