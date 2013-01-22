/**
 * Created with IntelliJ IDEA.
 * User: Matthew
 * Date: 13/01/13
 * Time: 21:03
 * To change this template use File | Settings | File Templates.
 */

import java.io.Serializable;
import java.net.URL;

class URLAndClassName implements Serializable {
    private final URL url;
    private final String className;

    public URLAndClassName(URL url, String className){
        this.url = url;
        this.className = className;
    }

    protected URL getURL(){return this.url;}
    protected String getClassName(){return this.className;}
}