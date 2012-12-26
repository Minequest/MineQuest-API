package com.theminequest.minequest.api.platform;

// IMMUTABlE!
public interface MQItemStack {
	
	int getAmount();
	short getData();
	<E extends MQMaterial> E getMaterial();
	boolean isEmpty();
	<E extends MQItemStack> E setAmount(int amount);
	<E extends MQItemStack> E setData(int data);
	<E extends MQItemStack> E setMaterial(MQMaterial material);
	<E> E getUnderlyingObject();
	
}
