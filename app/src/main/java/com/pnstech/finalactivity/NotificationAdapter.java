package com.pnstech.finalactivity;
    import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import java.util.ArrayList;

    public class NotificationAdapter  extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    private ArrayList<NotificationStore> profiles;
    private Context context;
    public NotificationAdapter(Context c, ArrayList<NotificationStore> p)
    {
        context=c;
        profiles=p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.notification_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(profiles.get(position).getTitle());
        holder.message.setText(profiles.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,message;

        public MyViewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.xtitle);
            message= itemView.findViewById(R.id.xmessage);
        }
    }
}


