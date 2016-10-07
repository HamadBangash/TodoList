package com.example.bangash.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bangash on 10/5/2016.
 */
public class dataManipulation {
    databaseHelper helper;
    SQLiteDatabase database;

    public dataManipulation(Context context) {
        helper = new databaseHelper(context);
    }


    ///////////////////////////////////////////Inserting Data//////////////////////////////////////////////
    public boolean InsertingData(Context context, String Title, int Year, int Month, int Day, int Hours, String Minutes, String AM_PM, String Description) {
        database = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TITLE", Title);
        cv.put("YEAR", Year);
        cv.put("MONTH", Month);
        cv.put("DAY", Day);
        cv.put("HOURS", Hours);
        cv.put("MINUTES", Minutes);
        cv.put("AM_PM", AM_PM);
        cv.put("DESCRIPTION", Description);
        long Result = database.insert("TABLE_ACTIVITIES", null, cv);
        database.close();
        if (Result != -1) {
            return true;
        } else {
            return false;
        }
    }

    /////////////////////////////////////////////////Retrieving All Data Through Id///////////////////////////////////
//    public List<String> RetrievingAllData(int id)
//    {
//        database = helper.getReadableDatabase();
//        List<String> DataList = new ArrayList<>();
//        String query = "SELECT * FROM TABLE_ACTIVITIES WHERE ID='"+id+"'";
//        Cursor cursor = database.rawQuery(query, null);
//        String Titles = "";
//        int Year = 0;
//        int Month = 0;
//        int Day = 0;
//        int Hours = 0;
//        String Minutes = "";
//        String AM_PM = "";
//        String Description = "";
//
//        if (cursor.getCount() > 0) {
//            cursor.moveToFirst();
//            do {
//                Titles = cursor.getString(cursor.getColumnIndex("TITLE"));
//                Year = cursor.getInt(cursor.getColumnIndex("YEAR"));
//                Month = cursor.getInt(cursor.getColumnIndex("MONTH"));
//                Day = cursor.getInt(cursor.getColumnIndex("DAY"));
//                Hours = cursor.getInt(cursor.getColumnIndex("HOURS"));
//                Minutes = cursor.getString(cursor.getColumnIndex("MINUTES"));
//                AM_PM = cursor.getString(cursor.getColumnIndex("AM_PM"));
//                Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
//                DataList.add(Titles);
//                DataList.add(String.valueOf(Year));
//                DataList.add(String.valueOf(Month));
//                DataList.add(String.valueOf(Day));
//                DataList.add(String.valueOf(Hours));
//                DataList.add(Minutes);
//                DataList.add(AM_PM);
//                DataList.add();
//            }
//            while (cursor.moveToNext());
//            database.close();
//
//        }
//        return DataList;
//    }

    ///////////////////////////////////////////Retrieving Titles//////////////////////////////////////////////

    public List<String> RetrievingTitles(Context context) {
        database = helper.getReadableDatabase();
        List<String> TitlesList = new ArrayList<>();
        String query = "SELECT TITLE FROM TABLE_ACTIVITIES";
        Cursor cursor = database.rawQuery(query, null);
        String Titles = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Titles = cursor.getString(cursor.getColumnIndex("TITLE"));
                TitlesList.add(Titles);
            }
            while (cursor.moveToNext());
            database.close();

        }
        return TitlesList;

    }

    ///////////////////////////////////////////Retrieving Year//////////////////////////////////////////////

    public List<Integer> RetrievingYear(Context context) {
        database = helper.getReadableDatabase();
        List<Integer> YearList = new ArrayList<>();
        String query = "SELECT YEAR FROM TABLE_ACTIVITIES";
        Cursor cursor = database.rawQuery(query, null);
        int Year = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Year = cursor.getInt(cursor.getColumnIndex("YEAR"));
                YearList.add(Year);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return YearList;
    }

    ///////////////////////////////////////////Retrieving Month//////////////////////////////////////////////

    public List<Integer> RetrievingMonth(Context context) {
        database = helper.getReadableDatabase();
        List<Integer> MonthList = new ArrayList<>();
        String query = "SELECT  MONTH FROM TABLE_ACTIVITIES";
        Cursor cursor = database.rawQuery(query, null);
        int Month = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Month = cursor.getInt(cursor.getColumnIndex("MONTH"));
                MonthList.add(Month);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return MonthList;
    }

    ///////////////////////////////////////////Retrieving Day//////////////////////////////////////////////

    public List<Integer> RetrievingDay(Context context) {
        database = helper.getReadableDatabase();
        List<Integer> DaysList = new ArrayList<>();
        String query = "SELECT DAY FROM TABLE_ACTIVITIES";
        Cursor cursor = database.rawQuery(query, null);
        int Day = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Day = cursor.getInt(cursor.getColumnIndex("DAY"));
                DaysList.add(Day);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return DaysList;
    }


    ///////////////////////////////////////////Retrieving Hours//////////////////////////////////////////////

    public List<Integer> RetrievingHours(Context context) {
        database = helper.getReadableDatabase();
        List<Integer> HoursList = new ArrayList<>();
        String query = "SELECT  HOURS FROM TABLE_ACTIVITIES";
        Cursor cursor = database.rawQuery(query, null);
        int Hours = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Hours = cursor.getInt(cursor.getColumnIndex("HOURS"));
                HoursList.add(Hours);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return HoursList;
    }


    ///////////////////////////////////////////Retrieving Minutes//////////////////////////////////////////////

    public List<String> RetrievingMinutes(Context context) {
        database = helper.getReadableDatabase();
        List<String> MinutesList = new ArrayList<>();
        String query = "SELECT MINUTES FROM TABLE_ACTIVITIES";
        Cursor cursor = database.rawQuery(query, null);
        String Minutes = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Minutes = cursor.getString(cursor.getColumnIndex("MINUTES"));
                MinutesList.add(Minutes);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return MinutesList;
    }

    ///////////////////////////////////////////Retrieving AM_PM//////////////////////////////////////////////

    public List<String> RetrievingAM_PM(Context context) {
        database = helper.getReadableDatabase();
        List<String> AM_PM_List = new ArrayList<>();
        String query = "SELECT AM_PM FROM TABLE_ACTIVITIES";
        Cursor cursor = database.rawQuery(query, null);
        String AM_PM = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                AM_PM = cursor.getString(cursor.getColumnIndex("AM_PM"));
                AM_PM_List.add(AM_PM);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return AM_PM_List;
    }


    ///////////////////////////////////////////Retrieving Description//////////////////////////////////////////////

    public List<String> RetrievingDescription(Context context) {
        database = helper.getReadableDatabase();
        List<String> DescriptionList = new ArrayList<>();
        String query = "SELECT DESCRIPTION FROM TABLE_ACTIVITIES";
        Cursor cursor = database.rawQuery(query, null);
        String Description = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                DescriptionList.add(Description);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return DescriptionList;
    }

    ////////////////////////////////////////////Retrieving ID'S /////////////////////////////////////////////////////

    public List<Integer> RetrievingIds(Context context) {
        database = helper.getReadableDatabase();
        List<Integer> IdsList = new ArrayList<>();
        String query = "SELECT ID FROM TABLE_ACTIVITIES";
        Cursor cursor = database.rawQuery(query, null);
        int id = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                id = cursor.getInt(cursor.getColumnIndex("ID"));
                IdsList.add(id);
            }
            while (cursor.moveToNext());
            database.close();

        }
        return IdsList;
    }

//////////////////////////////////////////////////Deleting Row Through Id///////////////////////////////////////////

    public void DeletingRow(int id) {
        database = helper.getWritableDatabase();
        String query = "DELETE FROM TABLE_ACTIVITIES WHERE ID='" + id + "'";
        database.execSQL(query);
        database.close();

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////Retrieving All Data Through Ids///////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////Retrieving Titles//////////////////////////////////////////////

    public List<String> RetrievingTitlesById(Context context, int id) {
        database = helper.getReadableDatabase();
        List<String> TitlesList = new ArrayList<>();
        String query = "SELECT TITLE FROM TABLE_ACTIVITIES WHERE ID='" + id + "'";
        Cursor cursor = database.rawQuery(query, null);
        String Titles = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Titles = cursor.getString(cursor.getColumnIndex("TITLE"));
                TitlesList.add(Titles);
            }
            while (cursor.moveToNext());
            database.close();

        }
        return TitlesList;

    }

    ///////////////////////////////////////////Retrieving Year//////////////////////////////////////////////

    public List<Integer> RetrievingYearById(Context context, int id) {
        database = helper.getReadableDatabase();
        List<Integer> YearList = new ArrayList<>();
        String query = "SELECT YEAR FROM TABLE_ACTIVITIES WHERE ID='" + id + "'";
        ;
        Cursor cursor = database.rawQuery(query, null);
        int Year = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Year = cursor.getInt(cursor.getColumnIndex("YEAR"));
                YearList.add(Year);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return YearList;
    }

    ///////////////////////////////////////////Retrieving Month//////////////////////////////////////////////

    public List<Integer> RetrievingMonthById(Context context, int id) {
        database = helper.getReadableDatabase();
        List<Integer> MonthList = new ArrayList<>();
        String query = "SELECT  MONTH FROM TABLE_ACTIVITIES WHERE ID='" + id + "'";
        Cursor cursor = database.rawQuery(query, null);
        int Month = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Month = cursor.getInt(cursor.getColumnIndex("MONTH"));
                MonthList.add(Month);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return MonthList;
    }

    ///////////////////////////////////////////Retrieving Day//////////////////////////////////////////////

    public List<Integer> RetrievingDayById(Context context, int id) {
        database = helper.getReadableDatabase();
        List<Integer> DaysList = new ArrayList<>();
        String query = "SELECT DAY FROM TABLE_ACTIVITIES  WHERE ID='" + id + "'";
        Cursor cursor = database.rawQuery(query, null);
        int Day = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Day = cursor.getInt(cursor.getColumnIndex("DAY"));
                DaysList.add(Day);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return DaysList;
    }


    ///////////////////////////////////////////Retrieving Hours//////////////////////////////////////////////

    public List<Integer> RetrievingHoursById(Context context, int id) {
        database = helper.getReadableDatabase();
        List<Integer> HoursList = new ArrayList<>();
        String query = "SELECT  HOURS FROM TABLE_ACTIVITIES  WHERE ID='" + id + "'";
        Cursor cursor = database.rawQuery(query, null);
        int Hours = 0;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Hours = cursor.getInt(cursor.getColumnIndex("HOURS"));
                HoursList.add(Hours);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return HoursList;
    }


    ///////////////////////////////////////////Retrieving Minutes//////////////////////////////////////////////

    public List<String> RetrievingMinutesById(Context context, int id) {
        database = helper.getReadableDatabase();
        List<String> MinutesList = new ArrayList<>();
        String query = "SELECT MINUTES FROM TABLE_ACTIVITIES  WHERE ID='" + id + "'";
        Cursor cursor = database.rawQuery(query, null);
        String Minutes = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Minutes = cursor.getString(cursor.getColumnIndex("MINUTES"));
                MinutesList.add(Minutes);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return MinutesList;
    }

    ///////////////////////////////////////////Retrieving AM_PM//////////////////////////////////////////////

    public List<String> RetrievingAM_PMById(Context context, int id) {
        database = helper.getReadableDatabase();
        List<String> AM_PM_List = new ArrayList<>();
        String query = "SELECT AM_PM FROM TABLE_ACTIVITIES  WHERE ID='" + id + "'";
        Cursor cursor = database.rawQuery(query, null);
        String AM_PM = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                AM_PM = cursor.getString(cursor.getColumnIndex("AM_PM"));
                AM_PM_List.add(AM_PM);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return AM_PM_List;
    }


    ///////////////////////////////////////////Retrieving Description//////////////////////////////////////////////

    public List<String> RetrievingDescriptionById(Context context, int id) {
        database = helper.getReadableDatabase();
        List<String> DescriptionList = new ArrayList<>();
        String query = "SELECT DESCRIPTION FROM TABLE_ACTIVITIES  WHERE ID='" + id + "'";
        Cursor cursor = database.rawQuery(query, null);
        String Description = "";
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
                DescriptionList.add(Description);
            }
            while (cursor.moveToNext());
            database.close();
        }
        return DescriptionList;
    }

    //////////////////////////////////////// Updating Data//////////////////////////////////////////////////

    public void UpdatingData(String Titles, int Year, int Month, int Day, int Hours, String Minutes, String AM_PM, String Description, int id) {
        database = helper.getReadableDatabase();
        List<String> DataList = new ArrayList<>();
        String query = "UPDATE TABLE_ACTIVITIES SET TITLE='" + Titles + "',YEAR='" + Year + "',MONTH='" + Month + "',DAY='" + Day + "',HOURS='" + Hours + "',MINUTES='" + Minutes + "',AM_PM='" + AM_PM + "',DESCRIPTION='" + Description + "' WHERE ID='" + id + "'";
        database.execSQL(query);

    }

}


