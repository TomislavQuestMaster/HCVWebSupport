package hcv.model;

import com.mysema.query.types.path.StringPath;
import hcv.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tdubravcevic
 */
public class DatabaseFilter {

	private ArrayList<TrainingTag> tags;
	private ArrayList<TrainingLevel> levels;
	//private List<TrainingType> types;
	private SortType sortBy;
	private SortDirection direction;
    private User owner;

    public DatabaseFilter() {
		tags = new ArrayList<TrainingTag>();
		levels = new ArrayList<TrainingLevel>();
	}

	public ArrayList<TrainingTag> getTags() {

		return tags;
	}

	public void setTags(ArrayList<TrainingTag> tags) {

		this.tags = tags;
	}

	public ArrayList<TrainingLevel> getLevels() {

		return levels;
	}

	public void setLevels(ArrayList<TrainingLevel> levels) {

		this.levels = levels;
	}

	public SortType getSortBy() {

		return sortBy;
	}

	public void setSortBy(SortType sortBy) {

		this.sortBy = sortBy;
	}

	public SortDirection getDirection() {

		return direction;
	}

	public void setDirection(SortDirection direction) {

		this.direction = direction;
	}

	public void addTag(TrainingTag tag){
		tags.add(tag);
	}

	public void addLevel(TrainingLevel level){
		levels.add(level);
	}

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
