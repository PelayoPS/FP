/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.controllers;

import java.util.List;

/**
 *
 * @author pelay
 */
public interface IDAO<T> {

    public boolean add(T o);

    public Object search(T o);

    public List<T> listAll();

}
