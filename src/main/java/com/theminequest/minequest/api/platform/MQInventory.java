package com.theminequest.minequest.api.platform;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MQInventory extends Serializable, List<MQItemStack> {
	
	void setData(int slot, int data);
	boolean addData(int slot, int amount);
	void setAmount(int slot, int amount);
	boolean addAmount(int slot, int amount);
	int getAmount(MQMaterial material);
	Map<Integer,MQItemStack> getAll(MQMaterial material);
	boolean contains(MQMaterial material, int amount);
	boolean containsExactly(MQMaterial material, int amount);
	void add(int firstSlot, int lastSlot, MQItemStack item);
	
}
