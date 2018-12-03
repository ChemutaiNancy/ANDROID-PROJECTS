package com.chemutai.project;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import dmax.dialog.SpotsDialog;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.MyViewHolder>{
    ArrayList<Passenger> passengerLists;
    Context ctx;
    SpotsDialog progress;

    public PassengerAdapter(ArrayList<Passenger> passengerLists, Context ctx) {
        this.passengerLists = passengerLists;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public PassengerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PassengerAdapter.MyViewHolder holder, int position) {
        final Passenger p = new Passenger();
        holder.name.setText(p.getName());
        holder.mail.setText(p.getEmail());
        holder.phoneNo.setText(p.getPhoneNo());
        holder.tdate.setText(p.gettDate()+"");
        holder.tFrom.setText(p.getTfrom());
        holder.tTo.setText(p.getTto());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
                dialog.setTitle("Edit Passenger "+p.getName());
                View root= LayoutInflater.from(ctx).inflate(R.layout.edit, null, false);//display(inflate
                final MaterialEditText name = root.findViewById(R.id.etName);
                final MaterialEditText mail = root.findViewById(R.id.etEmail);
                final MaterialEditText phoneNo = root.findViewById(R.id.etPhoneNo);
                final MaterialEditText tDate = root.findViewById(R.id.etDate);
                final MaterialEditText tFrom = root.findViewById(R.id.etFrom);
                final MaterialEditText tTo = root.findViewById(R.id.etTo);

                name.setText(p.getName());
                mail.setText(p.getEmail());
                phoneNo.setText(p.getPhoneNo());
                tDate.setText(p.gettDate()+"");
                tFrom.setText(p.getTfrom());
                tTo.setText(p.getTto());

                dialog.setView(root);


                dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String names = name.getText().toString();
                        String email=mail.getText().toString();
                        String phone=phoneNo.getText().toString();
                        String traDate=tDate.getText().toString();
                        String traFrom=tFrom.getText().toString();
                        String traTo=tTo.getText().toString();


                        if (names.isEmpty()  || email.isEmpty() || phone.isEmpty() || traDate.isEmpty() || traFrom.isEmpty() || traTo.isEmpty()){
                            Toast.makeText(ctx, "Fill all the values", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int phoneNo = Integer.parseInt(phone);
                        int travelDate = Integer.parseInt(traDate);
                        Date td= new Date(travelDate);
                        DateFormat d = new SimpleDateFormat("dd/mm/yyyy");

                        p.setName(names);
                        p.setEmail(email);
                        p.setPhoneNo(phoneNo);
                        p.settDate(td);
                        p.setTfrom(traFrom);
                        p.setTto(traTo);


                        notifyDataSetChanged();//refresh the list

                    }
                });

                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return passengerLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        MaterialEditText name, mail, phoneNo, tdate, tFrom, tTo;

        public MyViewHolder(View passengerView) {
            super(passengerView);

            name=passengerView.findViewById(R.id.etName);
            mail=passengerView.findViewById(R.id.etEmail);
            phoneNo=passengerView.findViewById(R.id.etPhoneNo);
            tdate=passengerView.findViewById(R.id.etDate);
            tFrom=passengerView.findViewById(R.id.etFrom);
            tTo=passengerView.findViewById(R.id.etTo);
        }
    }
}
