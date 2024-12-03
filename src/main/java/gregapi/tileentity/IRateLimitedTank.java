package gregapi.tileentity;


public interface IRateLimitedTank {
    void setLastInputRate(long mLastInputRate);
    long getLastInputRate();
}
