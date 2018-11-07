package com.example.patty.andoridlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.ArrayList;

public class ChatWindow extends Activity {
    public static final String ACTIVITY_NAME = "ChatWindow";
    Button sendBtn;
    ListView chatLstView;
    EditText chatEditTxt;
    ArrayList<String> chatMsgs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        sendBtn = findViewById(R.id.sendBtn);
        chatLstView = findViewById(R.id.chatLstView);
        chatEditTxt = findViewById(R.id.chatEditTxt);

        ChatAdapter messageAdapter = new ChatAdapter( this );
        chatLstView.setAdapter(messageAdapter);

        ChatDatabaseHelper dbHelper = new ChatDatabaseHelper(ChatWindow.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        final ContentValues cv = new ContentValues();

        Cursor cursor;
        cursor = db.rawQuery("SELECT MESSAGE FROM " + ChatDatabaseHelper.TABLE_NAME, null);
        int colIndex = cursor.getColumnIndex(dbHelper.KEY_MESSAGE);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String msgExisting = cursor.getString(colIndex);
            chatMsgs.add(msgExisting);

            Log.i(ACTIVITY_NAME, "SQL Message" + cursor.getString(cursor.getColumnIndex(dbHelper.KEY_MESSAGE)));
            Log.i(ACTIVITY_NAME, "Cursor's column count = " + cursor.getString(cursor.getColumnIndex(dbHelper.KEY_MESSAGE)));
            cursor.moveToNext();
        }


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = chatEditTxt.getText().toString();
                if (chatEditTxt != null) chatMsgs.add(message);
                messageAdapter.notifyDataSetChanged();
                chatEditTxt.setText("");

                ContentValues newRow = new ContentValues();
                newRow.put(ChatDatabaseHelper.KEY_MESSAGE, message);
                db.insert(ChatDatabaseHelper.TABLE_NAME, "", newRow);
            }
        });

    }

    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }
        public int getCount(){
            return chatMsgs.size();
        }
        public String getItem(int pos) {
            return chatMsgs.get(pos);
        }
        public View getView(int pos, View convertView, ViewGroup parent) {
            View result = null;
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            if (pos%2==0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            }
            TextView msg = (TextView)result.findViewById(R.id.msg_txt);
            msg.setText(getItem(pos));
            return result;
        }
        public long getItemId(int position) {
            return position;
        }
    }
}
