package hcv.model;

import java.util.List;

/**
 * @author tdubravcevic
 */
public class DatabaseFilter {

	private List<TrainingTag> tags;
	private List<TrainingLevel> levels;
	//private List<TrainingType> types;
	private SortType sortBy;
	private SortDirection direction;

	public List<TrainingTag> getTags() {

		return tags;
	}

	public void setTags(List<TrainingTag> tags) {

		this.tags = tags;
	}

	public List<TrainingLevel> getLevels() {

		return levels;
	}

	public void setLevels(List<TrainingLevel> levels) {

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
}
