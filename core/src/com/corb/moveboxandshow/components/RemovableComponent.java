package com.corb.moveboxandshow.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.Stack;

/**
 * Created by Calvin on 25/03/2017.
 */

public class RemovableComponent implements Component{

    public Stack<Entity> entitiesToRemove = new Stack<Entity>();



}
