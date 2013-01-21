/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 13/01/13
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;
import java.net.URL;

public class URLAndClassName implements Serializable {
    protected URL url;
    protected String className;

    public URLAndClassName(URL url, String className){
        this.url = url;
        this.className = className;
    }

    public URL getURL(){return this.url;}
    public String getClassName(){return this.className;}
}