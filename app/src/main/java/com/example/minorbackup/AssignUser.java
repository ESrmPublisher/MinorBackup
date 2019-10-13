package com.example.minorbackup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AssignUser extends RecyclerView.Adapter<AssignUser.ViewHolder> {

    private Context dContext ;
    private ArrayList<Users> usera ;

    String sname,sregid,susertype,spassword,sdept,syear,ssection,sclassincharge;

    public AssignUser(Context dContext, ArrayList<Users> usera) {
        this.dContext = dContext;
        this.usera = usera;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_assign,parent , false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Users user = usera.get(position);

        holder.name.setText(user.getFname()+" "+user.getLname());
        holder.regid.setText(user.getRegno());
        holder.usertype.setText(user.getEmailid());

        holder.acceptd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(dContext, user.getRegno()+(" Accepted"),Toast.LENGTH_LONG).show();
            }
        });

        holder.rejectd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // rejectuser();
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Toast.makeText(dContext, "User Details Card",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return usera.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView name,regid,usertype;
        Button acceptd,rejectd;
        CardView cardView ;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            regid = (TextView) itemView.findViewById(R.id.regid);
            usertype = (TextView) itemView.findViewById(R.id.usertype);
            acceptd = (Button) itemView.findViewById(R.id.acceptd);
            rejectd = (Button) itemView.findViewById(R.id.rejectd);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id_assign);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainlinear);
        }
    }


    private void rejectuser() {
    }

    private void acceptuser() {


    }



}