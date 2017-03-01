package inject;



import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by anqiansong on 2016/5/27.
 * 主题:[案例1]XJson,目前只支持数据类型,Array,Map,List
 */
public class XJson {
    private Object object;
    private String json;
    private Class clazz;
    public XJson(){}
    public XJson(Object object){
        if(object!=null) {
            this.object = object;
        }

    }



    public String toJsonString() throws UnHandleJsonException {
        if(object==null){
            return "";
        }
        if(isJsonObject(object)){ //是JsonObject
            if(isList(object)){
                return list2Object((List<Object>)object);
            }else if(isMap(object)){
                return map2Object((Map<Object,Object>)object);
            }else if(isCustomObject(object)){
                return obj2Json(object);
            }else{
                return basic2Json(object);
            }
        }else if(isJsonArray(object)){//JsonArray
            return array2Json(getArray(object));
        }else{//什么都不是
            throw new UnHandleJsonException("数据类型暂不支持!");
        }
    }
    public String toJsonString(Object object) throws UnHandleJsonException {
        if(object==null){
            return "";
        }
        if(isJsonObject(object)){
            if(isList(object)){
                return list2Object((List<Object>)object);
            }else if(isMap(object)){
                return map2Object((Map<Object,Object>)object);
            }else if(isCustomObject(object)){
                return obj2Json(object);
            }else{
                return basic2Json(object);
            }
        }else if(isJsonArray(object)){
            return array2Json(getArray(object));
        }else{
            throw new UnHandleJsonException("数据类型暂不支持!");
        }
    }





    /**
     * 判断是否为Object
     * @return true:object false:not object
     */
    public boolean isJsonObject(){
        return !isJsonArray();
    }

    /**
     * 判断是否为Object
     * @param object 待校验的object对象
     * @return true:object false:not object
     */
    public boolean isJsonObject(Object object){
        return !isJsonArray(object);
    }

    /**
     * 校验是否为array
     * @return true:array false:not array
     */
    public boolean isJsonArray(){
        return this.object.getClass().isArray();
    }

    /**
     * 校验是否为array
     * @param object 待校验的object对象
     * @return true:array false:not array
     */
    public boolean isJsonArray(Object object){
        if(object==null)return false;
        return object.getClass().isArray();
    }
    /*----------------------------------------private------------------------------------------*/

    /**
     * 校验是否为Byte类型
     * @return true:Byte false:not Byte
     */
    private boolean isByte(Object object){
        if(object!=null) {
            return object instanceof Byte;
        }
        return false;
    }
    /**
     * 校验是否为Integer类型
     * @return true:Integer false:not Integer
     */
    private boolean isInteger(Object object){
        if(object!=null) {
            return object instanceof Integer;
        }
        return false;
    }
    /**
     * 校验是否为Character类型
     * @return true:Character false:not Character
     */
    private boolean isCharacter(Object object){
        if(object!=null) {
            return object instanceof Character;
        }
        return false;
    }
    /**
     * 校验是否为Long类型
     * @param object 待判定object
     * @return true:Long false:not Long
     */
    private boolean isLong(Object object){
        if(object!=null) {
            return object instanceof Long;
        }
        return false;
    }
    /**
     * 校验是否为Float类型
     * @param object 待判断object
     * @return true:Float false:not Float
     */
    private boolean isLFloat(Object object){
        if(object!=null) {
            return object instanceof Float;
        }
        return false;
    }
    /**
     * 校验是否为Double类型
     * @param object 待判定object
     * @return true:Double false:not Double
     */
    private boolean isDouble(Object object){
        if(object!=null) {
            return object instanceof Double;
        }
        return false;
    }
    /**
     * 校验是否为Map类型
     * @param object 待判定object
     * @return true:Map false:not Map
     */
    private boolean isMap(Object object){
        if(object!=null) {
            return object instanceof Map;
        }
        return false;
    }
    /**
     * 校验是否为List类型
     * @param object 待判定object
     * @return true:List false:not List
     */
    private boolean isList(Object object){
        if(object!=null) {
            return object instanceof List;
        }
        return false;
    }
    /**
     * 校验是否为Array类型
     * @param clazz
     * @return true:array false:not array
     */
    private boolean isArray(Class clazz){
        return clazz.isArray();
    }
    /**
     * 校验是否为String类型
     * @param object 带判定object
     * @return true:String false:not String
     */
    private boolean isString(Object object){
        return object instanceof String;
    }


    /**
     * 校验是否为非系统类型(用户自定义类型)
     * @param object 待判定object
     * @return true:object false:not object
     */
    private boolean isCustomObject(Object object){
        if(object!=null) {
            return !(isByte(object)||isInteger(object)||isCharacter(object)||isLong(object)||
                    isDouble(object)||isLFloat(object)||isCharacter(object)||isMap(object)||
                    isList(object)||isJsonArray(object)||isString(object));
        }
        return false;
    }

    /**
     *
     * @param object 待判定object
     * @return Integer[]
     */
    private Integer[] getIntegerArray(Object object){
        int[] intArray=(int[])object;
        Integer[] array=new Integer[intArray.length];
        for(int i=0;i<intArray.length;i++){
            array[i]=intArray[i];
        }
        return array;
    }

    /**
     *
     * @param object 待判定object
     * @return Float[]
     */
    private Float[] getFloatArray(Object object){
        float[] floatArray=(float[])object;
        Float[] array=new Float[floatArray.length];
        for(int i=0;i<floatArray.length;i++){
            array[i]=floatArray[i];
        }
        return array;
    }

    /**
     *
     * @param object 待判定object
     * @return Double[]
     */
    private Double[] getDoubleArray(Object object){
        double[] doubleArray=(double[])object;
        Double[] array=new Double[doubleArray.length];
        for(int i=0;i<doubleArray.length;i++){
            array[i]=doubleArray[i];
        }
        return array;
    }

    /**
     *
     * @param object 待判定object
     * @return Long[]
     */
    private Long[] getLongArray(Object object){
        long[] longArray=(long[])object;
        Long[] array=new Long[longArray.length];
        for(int i=0;i<longArray.length;i++){
            array[i]=longArray[i];
        }
        return array;
    }

    /**
     *
     * @param object 待判定object
     * @return Byte[]
     */
    private Byte[] getByteArray(Object object){
        byte[] byteArray=(byte[])object;
        Byte[] array=new Byte[byteArray.length];
        for(int i=0;i<byteArray.length;i++){
            array[i]=byteArray[i];
        }
        return array;
    }

    /**
     *
     * @param object 待判定object
     * @return Character[]
     */
    private Character[] getCharacterArray(Object object){
        char[] charArray=(char[])object;
        Character[] array=new Character[charArray.length];
        for(int i=0;i<charArray.length;i++){
            array[i]=charArray[i];
        }
        return array;
    }

    /**
     * 处理基本数据类型情况,将其转化为引用类型
     * @param object
     * @return
     */
    private Object[] getArray(Object object){
        String name=object.getClass().getSimpleName();
        if(name.equals("int[]")){
            return getIntegerArray(object);
        }else if(name.equals("char[]")){
            return getCharacterArray(object);
        }else if(name.equals("byte[]")){
            return getByteArray(object);
        }else if(name.equals("long[]")){
            return getLongArray(object);
        }else if(name.equals("float[]")){
            return getFloatArray(object);
        }else if(name.equals("double[]")){
            return getDoubleArray(object);
        }
        return (Object[])object;
    }

    /**
     * 数组转json
     * @param array 待转数组
     * @return String
     */
    private String array2Json(Object[] array){
        int count=0;
        StringBuffer json=new StringBuffer("[");
        for(Object obj:array){
            if(isJsonArray(obj)){//JsonArray(List不属于Array的范畴)
                json.append(array2Json((getArray(obj))));//递归
            }else{//JsonObject
                if(isCustomObject(obj)){//是否为用户自定义类型
                    json.append(obj2Json(obj));
                }else{//系统类型
                    if(isList(obj)){//List
                        json.append(list2Object((List<Object>)obj));
                    }else if(isMap(obj)){//Map
                        json.append(map2Object((Map<Object,Object>)obj));
                    }else{//基本数据类型
                        json.append(basic2Json(obj));
                    }
                }
            }
            if(count!=array.length-1) {
                json.append(",");
            }
            count++;
        }
        return json.toString()+"]";
    }

    /**
     * 非系统类型转json(用户自定义对象类型转json)
     * @param object
     * @return String
     */
    private String obj2Json(Object object)  {
        int count=0;
        StringBuffer json=new StringBuffer("{");
        Class clazz=object.getClass();
        Field fields[]=clazz.getDeclaredFields();
        for(Field f:fields){
            f.setAccessible(true);
            String key = f.getName();
            Object value = "";
            if(key.equals("$change")){
                continue;
            }
            try {
                value = f.get(object);
                if (isJsonArray(value)) {
                    json.append("\"" + key + "\":" + array2Json(getArray(value)));
                } else if (isList(value)) {
                    json.append("\"" + key + "\":" + list2Object((List<Object>)value));
                } else if (isMap(value)) {
                    json.append("\"" + key + "\":" + map2Object((Map<Object,Object>)value));
                } else if (isCustomObject(value)) {
                    json.append("\"" + key + "\":" + obj2Json(value));  //递归
                } else {// 属于基本类型就解析其值 "string " int
                    json.append("\"" + key + "\":" + basic2Json(value));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (count != fields.length - 1) { //不是最后一个
                json.append(",");
            }
            count++;
        }



        return json.toString()+"}";   //最后假的
    }

    /**
     * List(及其子类)转json
     * @param list
     * @return String
     */
    private String list2Object(List<Object> list){
        int count=0;
        StringBuffer json=new StringBuffer("[");
        for(Object obj:list){
            if(isJsonArray(obj)){
                json.append(array2Json(getArray(obj)));
            }else if(isList(obj)){
                json.append(list2Object((List<Object>)obj));
            }else if(isMap(obj)){
                json.append(map2Object((Map<Object,Object>)obj));
            }else if(isCustomObject(obj)){
                json.append(obj2Json(obj));
            }else {
                json.append(basic2Json(obj));
            }
            if(count!=list.size()-1){
                json.append(",");
            }
            count++;
        }
        return json.toString()+"]";
    }

    /**
     * map(及其子类)转json key只能为基本数据类型和String
     * @return String
     */
    private String map2Object(Map<Object,Object> map){
        int count=0;
        StringBuffer json=new StringBuffer("{");
        for(Object key:map.keySet()){
            Object obj=map.get(key);
            if(isJsonArray(obj)){
                if(isString(key)) {
                    json.append("\"" + key + "\":" + array2Json(getArray(obj)));
                }else{
                    json.append( key +":" + array2Json(getArray(obj)));
                }
            }else if(isList(obj)){
                if(isString(key)) {
                    json.append("\"" + key + "\":" + list2Object((List<Object>) obj));
                }else{
                    json.append( key + ":" + list2Object((List<Object>) obj));
                }
            }else if(isMap(obj)){
                if(isString(key)) {
                    json.append("\"" + key + "\":" + map2Object((Map<Object, Object>) obj));
                }else{
                    json.append(  key + ":" + map2Object((Map<Object, Object>) obj));
                }
            }else if(isCustomObject(obj)){
                if(isString(key)) {
                    json.append("\"" + key + "\":" + obj2Json(obj));
                }else{
                    json.append(key + ":" + obj2Json(obj));
                }
            }else {
                if(isString(key)) {
                    json.append("\"" + key + "\":" + basic2Json(obj));
                }else{
                    json.append( key + ":" + basic2Json(obj));
                }
            }
            if(count!=map.size()-1){
                json.append(",");
            }
            count++;
        }
        return json.toString()+"}";
    }

    /**
     * 基本数据类型转Json
     * @param object
     * @return
     */
    private String basic2Json(Object object){
        if(isString(object)){
            return "\""+ String.valueOf(object)+"\"";
        }else{
            return String.valueOf(object);
        }
    }

}
