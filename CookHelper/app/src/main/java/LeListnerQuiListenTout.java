import android.content.DialogInterface;
import android.view.View;

import com.johicmes.cookhelper.R;

/**
 * Created by Daviiiiid on 2016-12-06.
 */
public class LeListnerQuiListenTout implements View.OnClickListener
{
    //si tu veux accéder à quelquechose de dynamique ou accéder aux méthodes du view utilise v.

    public void onClick(View v)
    {
        switch (v.findViewById(v.getId()).getId())
        {
            case R.id.editText7://exemple de comment ca marche, met le id approprié ici
            {
                //code ici () genre this is what happens when you click on this...
                break;
            }
        }
    }
}
