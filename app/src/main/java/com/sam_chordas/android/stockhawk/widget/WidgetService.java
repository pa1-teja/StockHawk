package com.sam_chordas.android.stockhawk.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;

/**
 * Created by pavan on 7/23/2016.
 */
public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataCenter(this.getBaseContext(),intent);
    }

   public class WidgetDataCenter implements RemoteViewsService.RemoteViewsFactory{

       private Context mContext;
       private int WidgetId;
       private Cursor mCursor;

       private final String[] DETAIL_COLUMNS = {
               QuoteColumns.SYMBOL,
               QuoteColumns.CHANGE,
               QuoteColumns.BIDPRICE,
               QuoteColumns.ISUP
       };


       public static final int COL_QUOTE_SYMBOL = 0;
       public static final int COL_QUOTE_CHANGE = 1;
       public static final int COL_QUOTE_BIDPRICE = 2;
       public static final int COL_QUOTE_ISUP = 3;


       public WidgetDataCenter(Context mContext, Intent intent) {
           this.mContext = mContext;
           WidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
       }

       @Override
       public void onCreate() {

       }

       @Override
       public void onDataSetChanged() {
            String sortOrder = QuoteColumns.SYMBOL + " ASC";
           if (mCursor != null)
               mCursor.close();

           mCursor = mContext.getContentResolver().query(QuoteProvider.Quotes.CONTENT_URI,
                   DETAIL_COLUMNS,
                   null,
                   null,
                   sortOrder);
       }

       @Override
       public void onDestroy() {
           if (mCursor != null)
               mCursor.close();
       }

       @Override
       public int getCount() {
           return mCursor == null ? 0 : mCursor.getCount();
       }

       @Override
       public RemoteViews getViewAt(int position) {
           if (position == AdapterView.INVALID_POSITION || mCursor == null)
           return null;

           RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.list_item_quote);
           if (mCursor.moveToPosition(position)){
               views.setTextViewText(R.id.stock_symbol, mCursor.getString(COL_QUOTE_SYMBOL));
               views.setTextViewText(R.id.bid_price, mCursor.getString(COL_QUOTE_BIDPRICE));
               views.setTextViewText(R.id.change, mCursor.getString(COL_QUOTE_CHANGE));
           }

           if (mCursor.getInt(COL_QUOTE_ISUP) == 1)
               views.setInt(R.id.change, "setBGRes", R.drawable.percent_change_pill_green);
           else
               views.setInt(R.id.change, "setBGRes", R.drawable.percent_change_pill_red);

           Intent intent = new Intent();
           mCursor.moveToPosition(position);
           Uri uri = QuoteProvider.Quotes.withSymbol(mCursor.getString(COL_QUOTE_SYMBOL));
           intent.setData(uri);

           views.setOnClickFillInIntent(R.id.widget_listView, intent);

           return views;
       }

       @Override
       public RemoteViews getLoadingView() {
           return null;
       }

       @Override
       public int getViewTypeCount() {
           return 1;
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
       public boolean hasStableIds() {
           return true;
       }
   }
}
