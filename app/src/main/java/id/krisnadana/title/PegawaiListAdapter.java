package id.krisnadana.title;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

import id.krisnadana.title.model.DetailPegawai;

public class PegawaiListAdapter extends RecyclerView.Adapter<PegawaiListAdapter.PegawaiViewHolder>{
    private final LinkedList<Integer> idPegawaiList;
    private final LinkedList<String> namaPegawaiList;
    private final LinkedList<String> alamatPegawaiList;
    private final LinkedList<String> fotoPegawaiList;
    private LayoutInflater mInflater;
    private final ClickListener listener;

    public PegawaiListAdapter(Context context, LinkedList<Integer> idPegawaiList, LinkedList<String> namaPegawaiList, LinkedList<String> alamatPegawaiList, LinkedList<String> fotoPegawaiList, ClickListener listener) {
        mInflater = LayoutInflater.from(context);
        this.idPegawaiList = idPegawaiList;
        this.namaPegawaiList = namaPegawaiList;
        this.alamatPegawaiList = alamatPegawaiList;
        this.fotoPegawaiList = fotoPegawaiList;
        this.listener = listener;
    }

    @Override
    public PegawaiListAdapter.PegawaiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.pegawailist_item, parent, false);
        return new PegawaiViewHolder(mItemView, this, listener);
    }

    @Override
    public void onBindViewHolder(PegawaiListAdapter.PegawaiViewHolder holder, int position) {
        String namaPegawai = namaPegawaiList.get(position);
        holder.pegawaiItemView.setText(namaPegawai);
        String alamatPegawai = alamatPegawaiList.get(position);
        holder.pegawaiItemAlamat.setText(alamatPegawai);
        String fotoPegawai = fotoPegawaiList.get(position);
        int drawable = holder.pegawaiItemFoto.getResources().getIdentifier(""+fotoPegawai, "drawable", LoginManagerActivity.PACKAGE_NAME);
        holder.pegawaiItemFoto.setImageResource(drawable);
    }

    @Override
    public int getItemCount() {
        return namaPegawaiList.size();
    }


    public class PegawaiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView pegawaiItemView;
        public final ImageView pegawaiItemFoto;
        public final TextView pegawaiItemAlamat;
        final PegawaiListAdapter mAdapter;
        private WeakReference<ClickListener> listenerRef;

        public PegawaiViewHolder(View itemView, PegawaiListAdapter Adapter, ClickListener listener) {
            super(itemView);

            listenerRef = new WeakReference<>(listener);
            this.pegawaiItemView = itemView.findViewById(R.id.pegawailist_nama);
            this.pegawaiItemFoto = itemView.findViewById(R.id.pegawailist_foto);
            this.pegawaiItemAlamat = itemView.findViewById(R.id.pegawailist_alamat);
            this.mAdapter = Adapter;

            itemView.setOnClickListener(this);
            this.pegawaiItemView.setOnClickListener(this);
            this.pegawaiItemFoto.setOnClickListener(this);
            this.pegawaiItemAlamat.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == pegawaiItemView.getId() || view.getId() == pegawaiItemFoto.getId() || view.getId() == pegawaiItemAlamat.getId()) {
                int idPegawai = idPegawaiList.get(Integer.parseInt(String.valueOf(getAdapterPosition())));
                DetailPegawai detailPegawai = new DetailPegawai(view.getContext());
                detailPegawai.dataPegawai(idPegawai);

                if(detailPegawai.getStatus() == 1){
                    detailPegawai.setGajiPegawai(idPegawai);
                    Intent intentDetailPegawai = new Intent(view.getContext(), DetailPegawaiActivity.class);
                    view.getContext().startActivity(intentDetailPegawai);
                }else{
                    Intent intentDetailPegawaiBayar = new Intent(view.getContext(), DetailPegawaiBayarActivity.class);
                    view.getContext().startActivity(intentDetailPegawaiBayar);
                }
                //Toast.makeText(view.getContext(), "ITEM PRESSED = " + idPegawai, Toast.LENGTH_SHORT).show();
            }
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }
    }
}
