package sdrfilter;

public class Filter
{
    private int length;
    private double[] delayLine;
    private float[] impulseResponse;
    private int count = 0;

    public Filter(float[] coefs)
    {
        length = coefs.length;
        impulseResponse = coefs;   
        delayLine = new double[length];
    }

    public float getOutputSample(float inputSample)
    {
       delayLine[count] = inputSample;
       float result = 0.0f;
       int index = count;
       for (int i=0; i<length; i++)
       {
		   result += impulseResponse[i] * delayLine[index--];
	       if (index < 0) index = length-1;
       }
       if (++count >= length) count = 0;
       return result;
  }
}