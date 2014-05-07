package hcv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Tomo.
 */
@Entity
@Table(name="trainings")
public class Training {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer uniqueDeviceId;
    private String owner;
    private Long lastUpdate;
    private String updatingDeviceName;

	private String description;
	private String keypoints;
	private ArrayList<TrainingTag> tags;
	private ArrayList<TrainingLevel> levels;
	private Short stress;
	private Short technics;
	private Short tactics;
	private Short fun;

    private Short defense;
    private Short offense;
    private Short goalie;

    public Training() {
		tags = new ArrayList<TrainingTag>();
		levels = new ArrayList<TrainingLevel>();
    }

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUniqueDeviceId() {
        return uniqueDeviceId;
    }

    public void setUniqueDeviceId(Integer uniqueDeviceId) {
        this.uniqueDeviceId = uniqueDeviceId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUpdatingDeviceName() {
        return updatingDeviceName;
    }

    public void setUpdatingDeviceName(String updatingDeviceName) {
        this.updatingDeviceName = updatingDeviceName;
    }

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getKeypoints() {

		return keypoints;
	}

	public void setKeypoints(String keypoints) {

		this.keypoints = keypoints;
	}

	public List<TrainingTag> getTags() {

		return tags;
	}

	public void setTags(ArrayList<TrainingTag> tags) {

		this.tags = tags;
	}

	public List<TrainingLevel> getLevels() {

		return levels;
	}

	public void setLevels(ArrayList<TrainingLevel> levels) {

		this.levels = levels;
	}

	public Short getStress() {

		return stress;
	}

	public void setStress(Short stress) {

		this.stress = stress;
	}

	public Short getTechnics() {

		return technics;
	}

	public void setTechnics(Short technics) {

		this.technics = technics;
	}

	public Short getTactics() {

		return tactics;
	}

	public void setTactics(Short tactics) {

		this.tactics = tactics;
	}

	public Short getFun() {

		return fun;
	}

	public void setFun(Short fun) {

		this.fun = fun;
	}

	public void addTag(TrainingTag tag){
		tags.add(tag);
	}

	public void addLevel(TrainingLevel level){
		levels.add(level);
	}

    public Short getDefense() {
        return defense;
    }

    public void setDefense(Short defense) {
        this.defense = defense;
    }

    public Short getOffense() {
        return offense;
    }

    public void setOffense(Short offense) {
        this.offense = offense;
    }

    public Short getGoalie() {
        return goalie;
    }

    public void setGoalie(Short goalie) {
        this.goalie = goalie;
    }

    public static class StressComparator implements Comparator<Training> {


		public int compare(Training o1, Training o2) {
			return o1.getStress().compareTo(o2.getStress());
		}
	}

	public static class TechnicsComparator implements Comparator<Training> {


		public int compare(Training o1, Training o2) {
			return o1.getTechnics().compareTo(o2.getTechnics());
		}
	}

	public static class TacticsComparator implements Comparator<Training> {


		public int compare(Training o1, Training o2) {
			return o1.getTactics().compareTo(o2.getTactics());
		}
	}

	public static class FunComparator implements Comparator<Training> {


		public int compare(Training o1, Training o2) {
			return o1.getFun().compareTo(o2.getFun());
		}
	}

    public static class DefenseComparator implements Comparator<Training> {


        public int compare(Training o1, Training o2) {
            return o1.getDefense().compareTo(o2.getDefense());
        }
    }

    public static class OffenseComparator implements Comparator<Training> {


        public int compare(Training o1, Training o2) {
            return o1.getOffense().compareTo(o2.getOffense());
        }
    }

    public static class GoalieComparator implements Comparator<Training> {


        public int compare(Training o1, Training o2) {
            return o1.getGoalie().compareTo(o2.getGoalie());
        }
    }

	public static class NameComparator implements Comparator<Training> {


		public int compare(Training o1, Training o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}
}
