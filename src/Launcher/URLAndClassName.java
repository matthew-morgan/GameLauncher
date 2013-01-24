package launcher;

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