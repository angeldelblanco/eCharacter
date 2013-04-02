/*******************************************************************************
 * <eAdventure Character Configurator> is a research project of the <e-UCM>
 *          research group.
 *
 *    Developed by: Alejandro Muñoz del Rey, Sergio de Luis Nieto and David González
 *    Ledesma.
 *    Under the supervision of Baltasar Fernández-Manjón and Javier Torrente
 * 
 *    Copyright 2012-2013 <e-UCM> research group.
 *  
 *     <e-UCM> is a research group of the Department of Software Engineering
 *          and Artificial Intelligence at the Complutense University of Madrid
 *          (School of Computer Science).
 *  
 *          C Profesor Jose Garcia Santesmases sn,
 *          28040 Madrid (Madrid), Spain.
 *  
 *          For more info please visit:  <http://character.e-ucm.es>, 
 *          <http://e-adventure.e-ucm.es> or <http://www.e-ucm.es>
 *  
 *  ****************************************************************************
 *      <eAdventure Character Configurator> is free software: you can 
 *      redistribute it and/or modify it under the terms of the GNU Lesser 
 *      General Public License as published by the Free Software Foundation, 
 *      either version 3 of the License, or (at your option) any later version.
 *  
 *      <eAdventure Character Configurator> is distributed in the hope that it 
 *      will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *      warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *      See the GNU Lesser General Public License for more details.
 *  
 *      You should have received a copy of the GNU Lesser General Public License
 *      along with <eAdventure Character Configurator>. If not, 
 *      see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package control;

import data.family.*;
import data.family.ScaleStageType.BoneController;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.bind.JAXBElement;
import types.StageType;
import types.SubStageTypeEnum;

public class FamilyControl 
{
    private Family family;
    
    public FamilyControl(Family family)
    {
        this.family = family;
    }
    
    /**************************** STAGES *************************************/
    public ArrayList<String> getStagesLabels()
    {
        ArrayList<String> listStagesLabels = new ArrayList<String>();
        ArrayList<ScaleStageType> listScaleStage = (ArrayList<ScaleStageType>) family.getStages().getScaleStage();
        Iterator<ScaleStageType> it = listScaleStage.iterator();
        while(it.hasNext())
        {
            ScaleStageType sst = it.next();
            listStagesLabels.add(sst.getStageLabel());
        }
        ArrayList<MultiStageType> listMultiStage = (ArrayList<MultiStageType>) family.getStages().getMultiStage();
        Iterator<MultiStageType> it2 = listMultiStage.iterator();
        while(it2.hasNext())
        {
            MultiStageType mst = it2.next();
            listStagesLabels.add(mst.getStageLabel());
        }
        return listStagesLabels;
    }
    
    public StageType getStagesTypes(String idStage)
    {
        ArrayList<ScaleStageType> listScaleStage = (ArrayList<ScaleStageType>) family.getStages().getScaleStage();
        Iterator<ScaleStageType> it = listScaleStage.iterator();
        while(it.hasNext())
        {
            ScaleStageType sst = it.next();
            if(sst.getStageLabel().equals(idStage)){
                return StageType.scaleStage;
            }
        }
        
        ArrayList<MultiStageType> listMultiStage = (ArrayList<MultiStageType>) family.getStages().getMultiStage();
        Iterator<MultiStageType> it2 = listMultiStage.iterator();
        while(it2.hasNext())
        {
            MultiStageType mst = it2.next();
            if(mst.getStageLabel().equals(idStage)){
                if(mst.getMeshSubStageOrTextureSubStage().size() == 1){
                    return StageType.singleStage;
                }
                else{
                    return StageType.multiStage;                
                }
            }
        }
        
        return null;
    }
    
    public int getNumSubStage(String multiStageLabel)
    {
        ArrayList<MultiStageType> listMultiStage = (ArrayList<MultiStageType>) family.getStages().getMultiStage();
        Iterator<MultiStageType> it = listMultiStage.iterator();
        while(it.hasNext())
        {
            MultiStageType mst = it.next();
            if(mst.getStageLabel().equals(multiStageLabel)){
                return mst.getMeshSubStageOrTextureSubStage().size();
            }
        }
        return -1;       
    }
    
    public ArrayList<String> getIdsSubStages(String stageLabel)
    {
        ArrayList<String> listSubStagesIds = new ArrayList<String>();
        ArrayList<ScaleStageType> listScaleStage = (ArrayList<ScaleStageType>) family.getStages().getScaleStage();
        Iterator<ScaleStageType> it = listScaleStage.iterator();
        while(it.hasNext())
        {
            ScaleStageType sst = it.next();
            if(sst.getStageLabel().equals(stageLabel)){
                listSubStagesIds.add(sst.getIdPanel());
            }
        }        
        ArrayList<MultiStageType> listMultiStage = (ArrayList<MultiStageType>) family.getStages().getMultiStage();
        Iterator<MultiStageType> it2 = listMultiStage.iterator();
        while(it2.hasNext())
        {
            MultiStageType mst = it2.next();
            if(mst.getStageLabel().equals(stageLabel)){
                ArrayList<JAXBElement<SubStageType>> listSubStages = (ArrayList<JAXBElement<SubStageType>>) mst.getMeshSubStageOrTextureSubStage();
                Iterator<JAXBElement<SubStageType>> it3 = listSubStages.iterator();
                while(it3.hasNext())
                {
                    JAXBElement<SubStageType> subStage = it3.next();
                    listSubStagesIds.add(subStage.getValue().getIdPanel());
                }
            }
        }
        return listSubStagesIds; 
    }
    
    public String getSubStageLabel(String multiStageLabel,String idSubStage)
    {
        ArrayList<MultiStageType> listMultiStage = (ArrayList<MultiStageType>) family.getStages().getMultiStage();
        Iterator<MultiStageType> it = listMultiStage.iterator();
        while(it.hasNext())
        {
            MultiStageType mst = it.next();
            if(mst.getStageLabel().equals(multiStageLabel)){
                ArrayList<JAXBElement<SubStageType>> listSubStages = (ArrayList<JAXBElement<SubStageType>>) mst.getMeshSubStageOrTextureSubStage();
                Iterator<JAXBElement<SubStageType>> it2 = listSubStages.iterator();
                while(it2.hasNext())
                {
                    JAXBElement<SubStageType> subStage = it2.next();
                    if(subStage.getValue().getIdPanel().equals(idSubStage)){
                        return subStage.getValue().getSubStageLabel();
                    }
                }
            }
        }
        return null;         
    }
    
    public SubStageTypeEnum getSubStageType(String multiStageLabel,String idSubStage)
    {
        ArrayList<MultiStageType> listMultiStage = (ArrayList<MultiStageType>) family.getStages().getMultiStage();
        Iterator<MultiStageType> it = listMultiStage.iterator();
        while(it.hasNext())
        {
            MultiStageType mst = it.next();
            if(mst.getStageLabel().equals(multiStageLabel)){
                ArrayList<JAXBElement<SubStageType>> listSubStages = (ArrayList<JAXBElement<SubStageType>>) mst.getMeshSubStageOrTextureSubStage();
                Iterator<JAXBElement<SubStageType>> it2 = listSubStages.iterator();
                while(it2.hasNext())
                {
                    JAXBElement<SubStageType> subStage = it2.next();
                    if(subStage.getValue().getIdPanel().equals(idSubStage)){
                        
                        //ACABAR
                        return null;
                    }
                }
            }
        }
        return null;         
    }
    
    /**************************************************************************/
    public ArrayList<String> getIdBonesController(String idStageLabel)
    {
        ArrayList<String> listIdBonesController = new ArrayList<String>();
        ArrayList<ScaleStageType> listScaleStage = (ArrayList<ScaleStageType>) family.getStages().getScaleStage();
        Iterator<ScaleStageType> it = listScaleStage.iterator();
        while(it.hasNext())
        {
            ScaleStageType s = it.next();
            if(s.getStageLabel().equals(idStageLabel)){
                ArrayList<BoneController> listBoneController = (ArrayList<BoneController>) s.getBoneController();
                Iterator<BoneController> it2 = listBoneController.iterator();
                while(it2.hasNext())
                {
                    BoneController bc = it2.next();
                    listIdBonesController.add(bc.getIdController());
                }                
            }
        }
        return listIdBonesController;
    }
    
    public String getBoneControllerLabel(String idStageLabel,String idBoneController)
    {
        ArrayList<ScaleStageType> listScaleStage = (ArrayList<ScaleStageType>) family.getStages().getScaleStage();
        Iterator<ScaleStageType> it = listScaleStage.iterator();
        while(it.hasNext())
        {
            ScaleStageType s = it.next();
            if(s.getStageLabel().equals(idStageLabel)){
                ArrayList<BoneController> listBoneController = (ArrayList<BoneController>) s.getBoneController();
                Iterator<BoneController> it2 = listBoneController.iterator();
                while(it2.hasNext())
                {
                    BoneController bc = it2.next();
                    if(bc.getIdController().equals(idBoneController)){
                        return bc.getControllerLabel();
                    }
                }                
            }
        }
        return null;
        
    }
    
    
    
    
}
