package dmcontact.org.com.democontact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoadContactAsyncTask.IListenerLoadFinished {

    private LoadContactAsyncTask mLoadContactAsyncTask;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mLoadContactAsyncTask = new LoadContactAsyncTask(this);
        mLoadContactAsyncTask.setOnLoadContactListener(this);
        mLoadContactAsyncTask.execute();
    }

    @Override
    public void onLoadContactFinished(ArrayList<Contact> contacts) {
        mRecyclerView.setAdapter(new ContactAdapter(this, contacts));
        Log.d("AAA", "onLoadContactFinished: " + contacts.size());
    }
}
