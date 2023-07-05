//package com.example.mobilecomputing;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class TaskAdapterClass {
//
//    public class ViewHolder extends RecyclerView.Adapter<ViewHolder.ViewHolder> {
//
//        List<TaskModelClass> task;
//        Context context;
//        DatabaseHelper databaseHelper;
//
//        public ViewHolder(List<TaskModelClass> task, Context context) {
//            this.task = task;
//            this.context = context;
//            databaseHelper = new DatabaseHelper(context);
//        }
//
//        @NonNull
//        @Override
//        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//            View view = layoutInflater.inflate(R.layout.task_item_list,parent,false);
//            ViewHolder viewHolder = new ViewHolder(view);
//            return viewHolder;
//        }
//
//
//        @Override
//        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//            final TaskModelClass taskModelClass = task.get(position);
//            holder.textViewID.setText(Integer.toString(taskModelClass.taskId));
//            holder.editText_Title.setText(taskModelClass.getTitle());
//            holder.editText_Description.setText(taskModelClass.getDescription());
//
//        }
//
//        @Override
//        public int getItemCount() {
//
//                return 0;
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder{
//            TextView textViewID;
//            EditText editText_Title;
//            EditText editText_Description;
//            Button button_edit;
//            Button button_delete;
//            public ViewHolder(@NonNull View itemView) {
//                super(itemView);
//
//                textViewID = itemView.findViewById(R.id.taskID);
//                editText_Title = itemView.findViewById(R.id.txtTitle);
//                editText_Description = itemView.findViewById(R.id.txtDescription);
//                button_delete = itemView.findViewById(R.id.btnDelete);
//                button_edit = itemView.findViewById(R.id.btnEdit);
//            }
//        }
//    }
//
//}


package com.example.mobilecomputing;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapterClass extends RecyclerView.Adapter<TaskAdapterClass.ViewHolder> {

    List<TaskModelClass> task;
    Context context;
    DatabaseHelper databaseHelper;

    public TaskAdapterClass(List<TaskModelClass> task, Context context) {
        this.task = task;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.task_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TaskModelClass taskModelClass = task.get(position);
        holder.textViewID.setText(String.valueOf(taskModelClass.getTaskId()));
        holder.editText_Title.setText(taskModelClass.getTitle());
        holder.editText_Description.setText(taskModelClass.getDescription());

        holder.button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringTitle = holder.editText_Title.getText().toString();
                String stringDescription = holder.editText_Description.getText().toString();

                databaseHelper.updateTask(new TaskModelClass(taskModelClass.getTaskId(),stringTitle,stringDescription));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                databaseHelper.deleteTask(taskModelClass.getTaskId());
                task.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return task.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewID;
        EditText editText_Title;
        EditText editText_Description;
        Button button_edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.taskID);
            editText_Title = itemView.findViewById(R.id.txtTitle);
            editText_Description = itemView.findViewById(R.id.txtDescription);
            button_delete = itemView.findViewById(R.id.btnDelete);
            button_edit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
