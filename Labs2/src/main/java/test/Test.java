package test;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//public class Test {
//    public static void main(String[] args) {
////        JSONObject empD = new JSONObject();
////        empD.put("firstName", "Gretta");
////        empD.put("lastName", "Suka");
////        empD.put("website", "howtodoinjava.com");
////
////        JSONObject empObj = new JSONObject();
////        empObj.put("employee", empD);
////
////        // Добавление работников в list
////        JSONArray empList = new JSONArray();
////        empList.add(empObj);
////
////        // Запись в файл
////        try (FileWriter file = new FileWriter("employees.json")){
////            file.write(empList.toJSONString());
////            file.flush();
////        } catch (IOException e){
////            e.printStackTrace();
////        }
//
//        // Чтение из файла
//        JSONParser jsonParser = new JSONParser();
//
//        try (FileReader reader = new FileReader("employees.json")){
//            // Читаем JSON файл
//            Object obj = jsonParser.parse(reader);
//
//            JSONArray empList = (JSONArray) obj;
//            System.out.println(empList);
//            empList.forEach( emp -> parseEmp( (JSONObject) emp ) );
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e){
//            e.printStackTrace();
//        }
//
//    }
//
//    private static void parseEmp(JSONObject employee){
//        //Get employee object within list
//        JSONObject employeeObject = (JSONObject) employee.get("employee");
//
//        //Get employee first name
//        String firstName = (String) employeeObject.get("firstName");
//        System.out.println(firstName);
//
//        //Get employee last name
//        String lastName = (String) employeeObject.get("lastName");
//        System.out.println(lastName);
//
//        //Get employee website name
//        String website = (String) employeeObject.get("website");
//        System.out.println(website);
//    }
//}

