/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.structural.composite.model;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Joao Pereira
 * 
 */
public abstract class CompositeShape extends Shape {

	List<Shape> shapes;

	public CompositeShape() {
		this.shapes = createShapesList();
	}

	/**
	 * Remove a shape from this shape childrens
	 * 
	 * @param shape
	 *            the shape to remove
	 * @return true if the shape was present and was removed, false if the shape
	 *         was not present
	 */
	public boolean removeShape(Shape shape) {
		// TODO: implement - done
		List<Shape> shapes_list = createShapesList();
		if(shapes_list.contains(shape)){
			if(this.shapes.contains(shape)){
				this.shapes.remove(shape);
				return true;
			}
			else{
				for(int i = 0; i < this.shapes.size(); i++){
					if (this.shapes.get(i).asComposite() != null) {
						boolean deleted = this.shapes.get(i).asComposite().removeShape(shape);
						if (deleted) {
							return true;
						}
					}
				}
			}
		}
		return false;

	}

	/**
	 * Return the total shapes count in case this is a composite
	 * 
	 * @return the total count of shapes if the shape is composite. -1 otherwise
	 */
	public int getShapeCount() {
		// TODO: implement - done
		List<Shape> shape_list = createShapesList();
		return shape_list.size();
	}

	/**
	 * Add a shape to this shape.
	 * 
	 * @param shape
	 *            The shape to add
	 * @throws ShapeDoesNotSupportChildren
	 *             if this shape is not a composite
	 */
	public void addShape(Shape shape) throws ShapeDoesNotSupportChildren{
		// TODO: Implement - done
		if(asComposite()==null) {
			throw new ShapeDoesNotSupportChildren();
		}
		this.shapes.add(shape);
	}

	public List<Shape> getShapes() {
		// TODO: Implement - done
		return this.shapes;

	}

	/**
	 * @param circle
	 * @return
	 */
	public List<Shape> getShapesByType(ShapeType circle) {
		// TODO: Implement - done
		List<Shape> shape_list = new ArrayList<Shape>();
		for (Shape shape : shapes) {
			if (shape.getType() == circle) {
				shape_list.add(shape);
			}
		}
		return shape_list;
	}

	/**
	 * Return all shapes that are leafs in the tree
	 * 
	 * @return
	 */
	public List<Shape> getLeafShapes() {
		// TODO: Implement - done
		List<Shape> shape_list = createShapesList();
		List<Shape> leafs = new ArrayList<Shape>();
		for (Shape shape : shape_list) {
			if( shape.asComposite() == null){
				leafs.add(shape);
			}
		}
		return leafs;
	}


	private List<Shape> addShapesFromList (List<Shape> shape_list){
		List<Shape> shapes_list = new ArrayList<Shape>();
		for (Shape shape: shape_list){
			if(shape.asComposite()==null){
				shapes_list.add(shape);
			}
			else{
				shapes_list.add(shape);
				shapes_list.addAll(addShapesFromList(shape.asComposite().getShapes()));
			}
		}
		return shapes_list;
	}

	/**
	 * Factory method for the list of children of this shape
	 * 
	 * @return
	 */
	protected List<Shape> createShapesList() {
		// TODO: Implement - done
		return addShapesFromList(shapes);
	}
}
