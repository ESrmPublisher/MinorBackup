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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AssignUser extends RecyclerView.Adapter<AssignUser.ViewHolder> {

    private Context dContext ;
    private ArrayList<Users> usera ;
    DatabaseReference firebaseDb;
    DatabaseReference databaseReference;
    Member member = new Member();
    register reg = new register();
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
                if(user.getRole().equals("Staff")) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("Accepted Users").child("Staff");
                    firebaseDb = FirebaseDatabase.getInstance().getReference("Member");
                }
                else if(user.getRole().equals("Admin")) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("Accepted Users").child("Admin");
                    firebaseDb = FirebaseDatabase.getInstance().getReference("Member");
                }
                else if(user.getRole().equals("Student")) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("Accepted Users").child("Student");
                    firebaseDb = FirebaseDatabase.getInstance().getReference("Member");
                }
                final Query mquery = firebaseDb.orderByChild("regno").equalTo(user.getRegno());
                mquery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            member.setFname(user.getFname());
                            member.setLname(user.getLname());
                            member.setEmailid(user.getEmailid());
                            member.setRegno(user.getRegno());
                            member.setPass(user.getPass());
                            databaseReference.push().setValue(member);
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(dContext, user.getRegno()+(" Accepted"),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        holder.rejectd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDb = FirebaseDatabase.getInstance().getReference("Member");
                Query mquery = firebaseDb.orderByChild("regno").equalTo(user.getRegno());
                mquery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(dContext, user.getRegno()+(" Rejected"),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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

        TextView name,regid,usertype,role;
        Button acceptd,rejectd;
        CardView cardView ;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            regid = (TextView) itemView.findViewById(R.id.regid);
            role =itemView.findViewById(R.id.role);
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