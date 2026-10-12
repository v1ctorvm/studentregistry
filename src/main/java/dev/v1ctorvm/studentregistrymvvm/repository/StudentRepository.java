package dev.v1ctorvm.studentregistrymvvm.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dev.v1ctorvm.studentregistrymvvm.config.MongoConfig;
import dev.v1ctorvm.studentregistrymvvm.model.Student;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Updates.set;

public class StudentRepository {

    private final MongoCollection<Student> col;


    public StudentRepository(MongoDatabase db) {
        this(db,"students");
    }

    public StudentRepository(MongoDatabase db, String collectionName) {
        this.col = db.getCollection(collectionName,Student.class);
    }

    // deleteAll - apagar todos os documentos da coleção em questão
    // Existe para que cada teste comece com uma base vazia, evitando vazamento de estado de testes anteriores
    public void deleteAll(){
        col.deleteMany(new Document());
    }

    public Student save(Student s){
        col.insertOne(s);
        return s;
    }

    public List<Student> findAll(){
        var list = new ArrayList<Student>();
        col.find().into(list);

        return list;
    }

    private Bson byId(String id){
        return Filters.or(
                Filters.eq("_id", id),
                Filters.eq("id", id)
        );
    }

    public void toggleFlag(String id) {
        var found = col.find(byId(id)).first();

        if (found != null){
            col.updateOne(byId(id),set("active",!found.isActive()));
        }
    }

    public void updateField(String id, double newGrade){
        col.updateOne(byId(id),set("grade",newGrade));


    }
}
