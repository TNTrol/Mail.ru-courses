
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDAO {
    private static final Type _listBooksType = new TypeToken<HashMap<String,Product>>() {
    }.getType();
    private HashMap<String, Product> _products;
    private String _fileName;

    public DataDAO(String fileName)
    {

        _products = readFromFile(fileName);
        if(_products == null)
            _products = new HashMap<>();
        _fileName = fileName;

    }

    public void addProduct(Product product)
    {
        if(_products.containsKey(product.getName()))
            return;
        _products.put(product.getName(), product);
        writeToFile(_products, _fileName);
    }

    public Product getProduct(String name)
    {
        return _products.get(name);
    }

    public List<Product> getAll(){
        List<Product> list = new ArrayList<>();
        for (Map.Entry entry: _products.entrySet()) {
            list.add((Product) entry.getValue());
        }
        return list;
    }

    private static HashMap<String, Product> readFromFile(String fileName)
    {
        try {
            return new Gson().fromJson(new BufferedReader(new FileReader(fileName)), _listBooksType);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void writeToFile(HashMap<String, Product> products, String fileName)
    {
        String str = new Gson().toJson(products);
        try {
            FileWriter fw = new FileWriter(fileName, false);
            fw.write(str);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
