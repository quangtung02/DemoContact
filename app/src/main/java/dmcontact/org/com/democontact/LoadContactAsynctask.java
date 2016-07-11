package dmcontact.org.com.democontact;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by nguyen.quang.tung on 7/11/2016.
 */
class LoadContactAsyncTask extends AsyncTask<Void, Void, ArrayList<Contact>> {

    public IListenerLoadFinished mListenerLoadContact;

    private ArrayList<Contact> mArrayListContacts;
    private ProgressDialog mProgressDialog;
    private Context mContext;

    public LoadContactAsyncTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mArrayListContacts = new ArrayList<Contact>();
        mProgressDialog = ProgressDialog.show(mContext, "Loading contact", "Please waiting...");
    }

    @Override
    protected ArrayList<Contact> doInBackground(Void... params) {

        Cursor cursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            mArrayListContacts.add(contact);
        }
        cursor.close();

        return mArrayListContacts;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(ArrayList<Contact> contacts) {
        super.onPostExecute(contacts);
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();

        Log.d("AAA", "onPostExecute " + contacts.size());
        if (contacts.size() > 0 && mListenerLoadContact != null)
            this.mListenerLoadContact.onLoadContactFinished(contacts);
    }

    public void setOnLoadContactListener(IListenerLoadFinished iListenerLoadFinished) {
        this.mListenerLoadContact = iListenerLoadFinished;
    }

    public interface IListenerLoadFinished {
        void onLoadContactFinished(ArrayList<Contact> contacts);
    }
}
