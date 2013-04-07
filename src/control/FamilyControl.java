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
import types.StageType;

public class FamilyControl 
{
    private Family family;
    
    public FamilyControl(Family family)
    {
        this.family = family;
    }
    
    /**************************** FAMILIES SELECTOR **************************/
    public ArrayList<String> getModelsLabels()
    {
        ArrayList<String> listModelsLables = new ArrayList<String>();
        ArrayList<ModelRefType> listModelRefType = (ArrayList<ModelRefType>)family.getModelsRef().getModelRef();
        Iterator<ModelRefType> it = listModelRefType.iterator();
        while(it.hasNext())
        {
            ModelRefType modelRefType = it.next();
            listModelsLables.add(modelRefType.getModelLabel());
        }
        return listModelsLables;
    }
    
    public String getModelIconPath(String modelLabel)
    {
        ArrayList<ModelRefType> listModelRefType = (ArrayList<ModelRefType>)family.getModelsRef().getModelRef();
        Iterator<ModelRefType> it = listModelRefType.iterator();
        while(it.hasNext())
        {
            ModelRefType modelRefType = it.next();
            if(modelRefType.getModelLabel().equals(modelLabel)){
                return modelRefType.getIconPath();
            }
        }
        return null;
    }
    
    public String getModelPath(String modelLabel)
    {
        ArrayList<ModelRefType> listModelRefType = (ArrayList<ModelRefType>)family.getModelsRef().getModelRef();
        Iterator<ModelRefType> it = listModelRefType.iterator();
        while(it.hasNext())
        {
            ModelRefType modelRefType = it.next();
            if(modelRefType.getModelLabel().equals(modelLabel)){
                return modelRefType.getModelPath();
            }
        }
        return null;
    }
    
    public int getNumModels()
    {
        return getModelsLabels().size();
    }
    
    public String getMetadataAuthor()
    {
        return family.getMetadata().getAuthor();
    }
    
    public String getMetadataDescription()
    {
        return family.getMetadata().getDescription();
    }
    
    public String getMetadataName()
    {
        return family.getMetadata().getName();
    }
    
    public String getMetadataURL()
    {
        return family.getMetadata().getURL();
    }
    
    public String getLanguagePath()
    {
        return family.getMetadata().getLanguagesPath();
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
        AnimationStageType as = family.getStages().getAnimationStage();
        listStagesLabels.add(as.getStageLabel());
            
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
                if(mst.getSubStage().size() == 1){
                    return StageType.singleStage;
                }
                else{
                    return StageType.multiStage;                
                }
            }
        }
        
        AnimationStageType as = family.getStages().getAnimationStage();
        if(as.getStageLabel().equals(idStage)){
            return StageType.animationStage;
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
                return mst.getSubStage().size();
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
                ArrayList<SubStageType> listSubStages = (ArrayList<SubStageType>) mst.getSubStage();
                Iterator<SubStageType> it3 = listSubStages.iterator();
                while(it3.hasNext())
                {
                    SubStageType subStage = it3.next();
                    listSubStagesIds.add(subStage.getIdPanel());
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
                ArrayList<SubStageType> listSubStages = (ArrayList<SubStageType>) mst.getSubStage();
                Iterator<SubStageType> it2 = listSubStages.iterator();
                while(it2.hasNext())
                {
                    SubStageType subStage = it2.next();
                    if(subStage.getIdPanel().equals(idSubStage)){
                        return subStage.getSubStageLabel();
                    }
                }
            }
        }
        return null;         
    }
    
    /************************   BONES CONTROLLERS   ***************************/
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
    
    /**************************** Animation Stage **************************/
    public ArrayList<String> getCamerasLabels()
    {
        ArrayList<String> listCameraLabels = new ArrayList<String>();
        AnimationStageType animationStage = family.getStages().getAnimationStage();
        ArrayList<CameraType> listCameras = (ArrayList<CameraType>) animationStage.getCamera();
        Iterator<CameraType> it = listCameras.iterator();
        while(it.hasNext())
        {
            CameraType camera = it.next();
            listCameraLabels.add(camera.getCameraLabel());
        }
        return listCameraLabels;
    }
    
    public int getNumCameras()
    {
        return getCamerasLabels().size();
    }
    
    public ArrayList<String> getQualityLabels()
    {
        ArrayList<String> listQualityLabels = new ArrayList<String>();
        AnimationStageType animationStage = family.getStages().getAnimationStage();
        ArrayList<FpsType> listQuality = (ArrayList<FpsType>) animationStage.getFps();
        Iterator<FpsType> it = listQuality.iterator();
        while(it.hasNext())
        {
            FpsType quality = it.next();
            listQualityLabels.add(quality.getQualityLabel());
        }
        return listQualityLabels;
    }
    
    
    /***********COMENTAR CON SERGIO****************************/
    public ArrayList<SubStageType> getSubStages()
    {
        ArrayList<SubStageType> listSubStages = new ArrayList<SubStageType>();
        ArrayList<MultiStageType> listMultiStages = (ArrayList<MultiStageType>) family.getStages().getMultiStage();
        Iterator<MultiStageType> it = listMultiStages.iterator();
        while(it.hasNext())
        {
            MultiStageType multiStage = it.next();
            ArrayList<SubStageType> listSubStagesType = (ArrayList<SubStageType>) multiStage.getSubStage();
            Iterator<SubStageType> it2 = listSubStagesType.iterator();
            while(it2.hasNext())
            {
                listSubStages.add(it2.next());
            }
        }
        return listSubStages;
    }
}
