package com.alphadev.api.repository;


import com.alphadev.Hoco;
import com.alphadev.api.annotation.HocoEntity;
import com.alphadev.api.exceptions.InvalidAnnotationException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class HocoRepository<K,T> implements IHocoRepository<K,T>{
    private final Class<T> entityType;
    private String hocoEntityName;
    public final MongoCollection<T> collection;

    public HocoRepository(){
        this.entityType = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
        this.initialize();
        MongoDatabase database = Hoco.getMongoDatabase();
        this.collection = database.getCollection(hocoEntityName, this.entityType);
    }

    private void initialize() {
        try {
            HocoEntity annotation = entityType.getAnnotation(HocoEntity.class);
            if(annotation==null) throw new InvalidAnnotationException("Anota\u00E7\u00E3o HocoEntity inv\u00E1lida :"+entityType.getName());
            hocoEntityName = annotation.value();
        }catch (InvalidAnnotationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T getById(K id) {
        Bson filter = Filters.eq("_id",id);
        return collection.find(filter).first();
    }

    @Override
    public void save(T entity) {
        collection.insertOne(entity);
    }

    @Override
    public void delete(K id) {
        Bson filter = Filters.eq("_id",id);
        collection.findOneAndDelete(filter);
    }

    @Override
    public void update(K entityId, T newEntity) {
        Bson filter = Filters.eq("_id",entityId);
        collection.findOneAndReplace(filter,newEntity);
    }

    @Override
    public List<T> getAll() {
        ArrayList<T> arrayList = new ArrayList<>();
        collection.find().into(arrayList);
        return arrayList;
    }
}
