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

import data.family.Family;
import java.util.ArrayList;
import java.util.Iterator;
import loader.Configuration;
import loader.XMLReader;
import types.StageType;

public class Control {
    
    private ArrayList<Family> families;
    private FamilyControl fc;
    
    public Control(Configuration config)
    {
        String familiesPath = config.getProperty(Configuration.FamiliesPath);
        XMLReader<Family> xmlReader = new XMLReader<Family>(familiesPath);
        families = xmlReader.readXML(Family.class);
    }
    
    public void setFamily(String nameFamily)
    {
        Iterator<Family> it = families.iterator();
        while(it.hasNext())
        {
            Family family = it.next();
            if (family.getMetadata().getName().equals(nameFamily))
            {
                fc = new FamilyControl(family);
            }
        }
    }
    
    public ArrayList<String> getFamiliesName()
    {
        ArrayList<String> listNames = new ArrayList<String>();
        Iterator<Family> it = families.iterator();
        while(it.hasNext())
        {
            Family family = it.next();
            listNames.add(family.getMetadata().getName());
        }
        return listNames;
    }
    
    public String getLanguageFamilyPath()
    {
        return fc.getLanguagePath();
    }
    
    public ArrayList<String> getModelsLabel()
    {
        return fc.getModelsLabels();
    }
    
    public String getModelIconPath(String modelLabel)
    {
        return fc.getModelIconPath(modelLabel);
    }
    
    public String getModelFamilyPath(String modelLabel)
    {
        return fc.getModelPath(modelLabel);
    }
    
    public int getNumModels()
    {
        return fc.getNumModels();
    }
    
    public String getMetadataFamilyName()
    {
        return fc.getMetadataName();
    }
    
    public String getMetadataFamilyURL()
    {
        return fc.getMetadataURL();
    }
    
    public String getMetadataFamilyDescription()
    {
        return fc.getMetadataDescription();
    }
    
    public String getMetadataFamilyAuthor()
    {
        return fc.getMetadataAuthor();
    }
    
    public ArrayList<String> getStagesLabels()
    {
        return fc.getStagesLabels();
    }
    
    public StageType getStagesTypes(String idStage)
    {
        return fc.getStagesTypes(idStage);
    }
    
    public int getNumCameras()
    {
        return fc.getNumCameras();
    }
    
    public ArrayList<String> getCamerasLabels()
    {
        return fc.getCamerasLabels();
    }
    
    public ArrayList<String> getQualityLabels()
    {
        return fc.getQualityLabels();
    }
    
    public ArrayList<String> getIdsSubStages(String stageLabel)
    {
        return fc.getIdsSubStages(stageLabel);
    }
    
    public int getNumSubStages(String stageLabel)
    {
        return fc.getNumSubStage(stageLabel);
    }
    
    public String getSubStageLabel(String multiStageLabel,String idSubStage)
    {
        return fc.getSubStageLabel(multiStageLabel, idSubStage);
    }
    
    public ArrayList<String> getIdBonesController(String idStageLabel)
    {
        return fc.getIdBonesController(idStageLabel);
    }
    
    public String getBoneControllerLabel(String idStageLabel,String idBoneController)
    {
        return fc.getBoneControllerLabel(idStageLabel, idBoneController);
    }
}
