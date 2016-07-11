package dmcontact.org.com.democontact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyen.quang.tung on 7/11/2016.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context mContext;
    private List<Contact> mListContact;
    private LayoutInflater mLayoutInflater;

    public ContactAdapter(Context context, ArrayList<Contact> arrayList) {
        this.mContext = context;
        this.mListContact = arrayList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = mListContact.get(position);
        if (contact != null) {
            holder.mTxtName.setText(contact.getName().toString());
            holder.mTxtPhone.setText(contact.getPhone().toString());
        } else {
            Log.d("AAA", "Contact is null.");
        }
    }

    @Override
    public int getItemCount() {
        return mListContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTxtName;
        public TextView mTxtPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            mTxtName = (TextView) itemView.findViewById(R.id.text_view_name);
            mTxtPhone = (TextView) itemView.findViewById(R.id.text_view_phone_number);
        }
    }
}
