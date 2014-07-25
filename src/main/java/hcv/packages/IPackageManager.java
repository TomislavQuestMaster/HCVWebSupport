package hcv.packages;

import com.itextpdf.text.DocumentException;
import hcv.core.ICrud;
import hcv.core.ISync;
import hcv.packages.model.PackageItem;
import hcv.trainings.model.Training;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author tdubravcevic
 */
public interface IPackageManager extends ICrud<PackageItem>, ISync<PackageItem> {

	//PackageStatistics calculateStatistics (Package package);

	List<PackageItem> getAllPackages();

	List<PackageItem> getAllPackagesInGroup(PackageItem group);

	void pack(PackageItem packageItem);

	void generateSheet(PackageItem packageItem) throws IOException, DocumentException;

	//void updatePackages(long oldUid, TrainingDetails training);

}
