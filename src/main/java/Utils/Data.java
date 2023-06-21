package Utils;

import BasicDataType.DataTypeBase;

import java.util.ArrayList;

public class Data {
    private DataTypeBase[] data = null;
    public static final Data NULL = new Data();

    public void add(DataTypeBase d){
        if (data == null) {
            data = new DataTypeBase[]{d};
        }else {
            ArrayList<DataTypeBase> temp = new ArrayList<>();
            for (DataTypeBase i : data) {
                temp.add(i);
            }
            temp.add(d);
            data = temp.toArray(new DataTypeBase[0]);
        }
    }

    public DataTypeBase get(int index){
        return data[index];
    }

    public void setData(int index, DataTypeBase d){
        data[index] = d;
    }

    public int size(){
        return data.length;
    }
}
