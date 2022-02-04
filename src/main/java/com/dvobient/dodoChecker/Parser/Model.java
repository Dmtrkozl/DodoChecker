package com.dvobient.dodoChecker.Parser;

import com.dvobient.dodoChecker.Parser.Entity.Slot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final Logger logger = LoggerFactory.getLogger(Model.class);
    private List<Slot> oldSlotModel;
    private List<Slot> newSlotModel;

    public List<Slot> getNewSlotModel() {
        return newSlotModel != null ? newSlotModel : oldSlotModel;
    }

    public void addData(List<Slot> data) {
        logger.info("addData method invoked");
        if (oldSlotModel == null) {
            oldSlotModel = new ArrayList<>(data);
            logger.info("set oldSlotModel");
        } else {
            newSlotModel = new ArrayList<>(data);
            logger.info("set newSlotModel");
        }
    }

    // при поступлении результатов, копирует их в старый список далее выявляет результат после чего возвращает строку обновления
    public List<Slot> findDiff() {
        logger.info("findDiff method invoked");
        logger.info("OldSlotModel:");
        for (Slot slot : oldSlotModel) {
            logger.info(slot.toString());
        }
        if (newSlotModel == null) return oldSlotModel;
        else {
            List<Slot> newSlotModelCopy = new ArrayList<>(newSlotModel);
            newSlotModel.removeAll(oldSlotModel);
            oldSlotModel = newSlotModelCopy;
            logger.info("NewSlotModel after removing duplicates :\n" + newSlotModel);
            for (Slot slot : newSlotModel) {
                logger.info(slot.toString());
            }
            logger.info("OldSlotModelAfterCopy: ");
            for (Slot slot : oldSlotModel) {
                logger.info(slot.toString());
            }
            return newSlotModel;
        }
    }
}
