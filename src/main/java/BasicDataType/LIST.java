package BasicDataType;

import java.util.ArrayList;

public class LIST extends DataTypeBase implements Iterable{

    private ArrayList<DataTypeBase> list;
    public LIST(){
        this.type = "list";
        list = new ArrayList<DataTypeBase>();
    }

    public LIST(String name, int initCapacity){
        this.type = "list";
        this.name = name;
        this.list = new ArrayList<DataTypeBase>(initCapacity);
    }

    public LIST(String name){
        this.type = "list";
        this.name = name;
        this.list = new ArrayList<DataTypeBase>();
    }

    public int[] SliceUp(String code){
        String[] codes = code.split(":");
        int[] result;
        if(codes.length==2){
            result = new int[2];
            if(codes[0].equals("")){
                int num = Integer.parseInt(codes[1]);
                if(num < 0) num = list.size()+num;
                result[1] = num;
                return result;
            } else if (codes[1].equals("")){
                int num = Integer.parseInt(codes[0]);
                if(num < 0) num = list.size()+num;
                result[0] = num;
                return result;
            }else {
                return new int[]{Integer.parseInt(codes[0]), Integer.parseInt(codes[1])};
            }
        } else if (codes.length == 3) {
            result = new int[3];
            for(int i = 0; i < 3; i++){
                if(codes[i].equals("")){
                    result[i] = 0;
                } else if (Integer.parseInt(codes[i]) < 0){
                    result[i] = list.size()+Integer.parseInt(codes[i]);
                }else{
                    result[i] = Integer.parseInt(codes[i]);
                }
            }
        } else if (codes.length == 1) {
            return new int[]{Integer.parseInt(codes[0])};
        }
        return null;
    }

    public ArrayList<DataTypeBase> getSub(String code){
        int[] index = SliceUp(code);
        ArrayList<DataTypeBase> result = new ArrayList<DataTypeBase>();
        if(index.length == 2){
            for(int i = index[0]; i <= index[1]; i++){
                result.add(list.get(i));
            }
        } else if (index.length == 3){
            for(int i = index[0]; i < index[1]; i+=index[2]){
                result.add(list.get(i));
            }
        }
        return result;
    }

    public DataTypeBase getElement(String code){
        int[] index = SliceUp(code);
        if(index.length == 1){
            return list.get(index[0]);
        }else {
            return null;
        }
    }

    public void append(DataTypeBase var){
        list.add(var);
    }

    public ArrayList<DataTypeBase> getList(){
        return list;
    }

    public void merge(LIST var){
        list.addAll(var.getList());
    }

    public boolean contains(DataTypeBase var){
        return list.contains(var);
    }

    public boolean contains(String var){
        for(DataTypeBase var1 : list){
            if(var1.name().equals(var)) return true;
        }
        return false;
    }
}
