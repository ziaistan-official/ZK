package juloo.keyboard2;

public final class Gesture
{
  /** The pointer direction that caused the last state change.
      Integer from 0 to 15 (included). */
  int current_dir;
  final Corner starting_corner;

  State state;

  public Gesture(int starting_direction)
  {
    current_dir = starting_direction;
    state = State.Swiped;
    starting_corner = get_corner(starting_direction);
  }

  enum State
  {
    Cancelled,
    Swiped,
    Rotating_clockwise,
    Rotating_anticlockwise,
    Ended_swipe,
    Ended_center,
    Ended_clockwise,
    Ended_anticlockwise
  }

  enum Name
  {
    None,
    Swipe,
    Roundtrip,
    Circle,
    Anticircle,
    CircleNE,
    AnticircleNE,
    CircleSE,
    AnticircleSE,
    CircleSW,
    AnticircleSW,
    CircleNW,
    AnticircleNW,
  }

  enum Corner {
    NE, SE, SW, NW, None
  }

  /** Return the corner that correspond to a given direction. */
  static Corner get_corner(int direction)
  {
    // 0 is up, 4 is right, 8 is down, 12 is left.
    if (direction >= 1 && direction <= 3) return Corner.NE;
    if (direction >= 5 && direction <= 7) return Corner.SE;
    if (direction >= 9 && direction <= 11) return Corner.SW;
    if (direction >= 13 && direction <= 15 || direction == 0) return Corner.NW;
    return Corner.None;
  }

  /** Angle to travel before a rotation gesture starts. A threshold too low
      would be too easy to reach while doing back and forth gestures, as the
      quadrants are very small. In the same unit as [current_dir] */
  static final int ROTATION_THRESHOLD = 2;

  /** Return the currently recognized gesture. Return [null] if no gesture is
      recognized. Might change everytime [changed_direction] return [true]. */
  public Name get_gesture()
  {
    if (state == State.Cancelled) return Name.None;
    if (state == State.Swiped || state == State.Ended_swipe) return Name.Swipe;
    if (state == State.Ended_center) return Name.Roundtrip;
    if (state == State.Rotating_clockwise || state == State.Ended_clockwise) {
        switch (starting_corner) {
            case NE: return Name.CircleNE;
            case SE: return Name.CircleSE;
            case SW: return Name.CircleSW;
            case NW: return Name.CircleNW;
            default: return Name.Circle;
        }
    }
    if (state == State.Rotating_anticlockwise || state == State.Ended_anticlockwise) {
        switch (starting_corner) {
            case NE: return Name.AnticircleNE;
            case SE: return Name.AnticircleSE;
            case SW: return Name.AnticircleSW;
            case NW: return Name.AnticircleNW;
            default: return Name.Anticircle;
        }
    }
    return Name.None; // Unreachable
  }

  public boolean is_in_progress()
  {
    switch (state)
    {
      case Swiped:
      case Rotating_clockwise:
      case Rotating_anticlockwise:
        return true;
    }
    return false;
  }

  public int current_direction() { return current_dir; }

  /** The pointer changed direction. Return [true] if the gesture changed
      state and [get_gesture] return a different value. */
  public boolean changed_direction(int direction)
  {
    int d = dir_diff(current_dir, direction);
    boolean clockwise = d > 0;
    switch (state)
    {
      case Swiped:
        if (Math.abs(d) < Config.globalConfig().circle_sensitivity)
          return false;
        // Start a rotation
        state = (clockwise) ?
          State.Rotating_clockwise : State.Rotating_anticlockwise;
        current_dir = direction;
        return true;
      // Check that rotation is not reversing
      case Rotating_clockwise:
      case Rotating_anticlockwise:
        current_dir = direction;
        if ((state == State.Rotating_clockwise) == clockwise)
          return false;
        state = State.Cancelled;
        return true;
    }
    return false;
  }

  /** Return [true] if [get_gesture] will return a different value. */
  public boolean moved_to_center()
  {
    switch (state)
    {
      case Swiped: state = State.Ended_center; return true;
      case Rotating_clockwise: state = State.Ended_clockwise; return false;
      case Rotating_anticlockwise: state = State.Ended_anticlockwise; return false;
    }
    return false;
  }

  /** Will not change the gesture state. */
  public void pointer_up()
  {
    switch (state)
    {
      case Swiped: state = State.Ended_swipe; break;
      case Rotating_clockwise: state = State.Ended_clockwise; break;
      case Rotating_anticlockwise: state = State.Ended_anticlockwise; break;
    }
  }

  static int dir_diff(int d1, int d2)
  {
    final int n = 16;
    // Shortest-path in modulo arithmetic
    if (d1 == d2)
      return 0;
    int left = (d1 - d2 + n) % n;
    int right = (d2 - d1 + n) % n;
    return (left < right) ? -left : right;
  }
}
