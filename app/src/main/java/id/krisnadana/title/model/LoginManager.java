package id.krisnadana.title.model;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

public class LoginManager extends SQLiteOpenHelper{
    private static String DB_PATH= "data/data/id.krisnadana.title/databases/";
    private static String DB_NAME = "gaji_pegawai.db";
    private static int DB_VERSION = 3;
    private final Context context;

    public LoginManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public boolean loginAuth(String usernameInput, String passwordInput) {
        SQLiteDatabase auth = null;
        String path = DB_PATH + DB_NAME;

        try{
            auth = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if(auth != null){
                String sql = "SELECT username FROM admin where username='"+usernameInput+"' and password='"+passwordInput+"' and role='MANAGER' and STATUS=1 LIMIT 1";
                Cursor cursor = auth.rawQuery(sql, null);

                if(cursor.getCount() == 0){
                    auth.close();
                    return false;
                }else{
                    auth.close();
                    return true;
                }
            }else{
                return false;
            }
        }catch(SQLiteException e){
            if(auth != null){
                auth.close();
            }
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
