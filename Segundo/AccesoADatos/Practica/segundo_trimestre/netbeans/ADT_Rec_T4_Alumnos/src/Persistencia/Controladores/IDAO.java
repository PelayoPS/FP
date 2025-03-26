/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia.Controladores;

import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IDAO {
    boolean add(Object obj);

    List listAll();

    boolean update(Object obj);

    Object search(Object Key);
}
