package id.smkn4.pwpb.inputuserandcustomlistview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

//Kita memerlukan untuk meng-extends class CustomAdapter kita dari ArrayAdapter
public class MyListAdapter extends ArrayAdapter<Hero> {

    //List Hero
    List<Hero> heroList;

    //Activity Context
    Context context;

    //layout untuk list item
    int resource;

    //inisialisasi constructor
    public MyListAdapter(Context context, int resource, List<Hero> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    //mengembalikan Listview item menjadi view
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //get View
        View view = layoutInflater.inflate(resource, null, false);

        //get elements from view
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewTeam = view.findViewById(R.id.textViewTeam);
        TextView buttonDelete = view.findViewById(R.id.buttonDelete);

        //get letak spesific Hero
        Hero hero = heroList.get(position);

        //Mengisi list Item
        imageView.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        textViewName.setText(hero.getName());
        textViewTeam.setText(hero.getTeam());

        //Menambahkan Aksi untuk mendelete item
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //memanggil function remove dan mengirim posisinya
                removeHero(position);
            }
        });
        //mengembalikan view
        return view;
    }

    //method remove hero
    public void removeHero(final int position) {
        //AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Apakah Kamu yakin ingin menghapus ini ?");

        //jika jawabannya Ya
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //remove item
                heroList.remove(position);
                //reload list
                notifyDataSetChanged();
            }
        });
        //jika jawabannya Tidak
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //Membuat dan menampilkan alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
