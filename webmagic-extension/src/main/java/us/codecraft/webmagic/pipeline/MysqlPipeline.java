package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.entity.InfoEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class MysqlPipeline implements Pipeline {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String username = "root";
    private static final String password = "lkf@123456";
    private Connection connection=null;
    private PreparedStatement preparedStatement=null;

    private void dbManager(String sql){
        try{
            Class.forName(name);
            connection = DriverManager.getConnection(url, username, password);
            preparedStatement = connection.prepareStatement(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void close() throws  Exception{
        try{
            this.connection.close();
            this.preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    @Override
    public void process(ResultItems resultItems, Task task){
        String sql = "SELECT * FROM spider";
        dbManager(sql);  //实例化
        String title, description;
        List<InfoEntity> infoEntityList = (List<InfoEntity>)resultItems.get("dataInfo");

        try{
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){                  //若有数据，就输出
                title = result.getString(3);
                description = result.getString(4);
                //显示出每一行数据
                System.out.println(title + "  " + description);
            }
            result.close();
            close();

        }catch (Exception e){
            e.printStackTrace();
        }

        sql =  "INSERT INTO spider(taskid,title,description) VALUES (?,?,?)";
        dbManager(sql);  //实例化
        try{
            if(!infoEntityList.isEmpty()){
                for(InfoEntity item : infoEntityList){
                    preparedStatement.setString(1,"oschina");
                    preparedStatement.setString(2,item.getTitle());
                    preparedStatement.setString(3,item.getDescription());
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
            close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
