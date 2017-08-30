package com.example.blitz.protesiss;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;

import static android.R.color.darker_gray;
import static android.R.color.holo_blue_light;

// declaracion de todas las variables
public class MainActivity extends Activity {

    private String TAG = "MAINACTIVITY";
    private static final int REQUEST_ENABLE_BLUETOOTH = 1 ;
    private BluetoothSocket btSocket;
    private BluetoothAdapter bAdapter;

    private ConnectedThread mConnectedThread;

    private Button an;
    private Button bn;
    private Button cn;
    private Button dn;
    private Button en;
    private Button fn;
    private Button gn;
    private Button hn;
    private Button in;
    private Button jn;
    private Button kn;
    private Button ln;
    private Button rn;
    private Button on;
    private Button un;
    private FloatingActionButton fab;

    private void configurarAdaptadorBluetooth() {
        bAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bAdapter == null) {
            fab.setEnabled(false);
            return;
        }

        if (bAdapter.isEnabled())
            fab.setBackgroundColor(getResources().getColor(darker_gray));
        else
            fab.setBackgroundColor(getResources().getColor(holo_blue_light));
    }

    private final BroadcastReceiver bReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)){
                final int estado = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch (estado){
                    case BluetoothAdapter.STATE_OFF:{
                        fab.setBackgroundColor(getResources().getColor(holo_blue_light));
                        break;
                    }
                    case BluetoothAdapter.STATE_ON:{
                        fab.setBackgroundColor(getResources().getColor(darker_gray));
                        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,120);
                        startActivity(discoverableIntent);
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    };


    // AQUI VAS A OBTENER TODAS LAS VIEWS DE UNA VIEWGROUP
    //
    // View: Botones, Textos, Entradas de Text, listas, etc
    // ViewGroup: Relative Layout, Linear layout, ConstraintLayout.
    //
    // ejemplo:
    //
    //  <RelativeLayout>  <- ViewGroup
    //      <LinearLayout> <- ViewGroup
    //          <TextView></TextView> <- View
    //      </LinearLayout>
    //  </RelativeLayout>
    //
    // TAMBIEN AQUI VAS A INSTANCIAR TODOS LOS OBJETOS,
    // YA QUE, CUANDO SE LLAMA EL METODO ONCREATE,
    // TU APLICACION SE COLOCA EN MEMORIA, SI LO HACES
    // ANTES, TE VA A LANZAR UN ERROR.
    //
    // Como por ejemplo: Crear conexiones con una base de datos,
    // Instanciar tus Fragmentos, entre otros.
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obteniendo botones de la vista.
        an = (Button) findViewById(R.id.button);
        bn = (Button) findViewById(R.id.button2);
        cn = (Button) findViewById(R.id.button3);
        dn = (Button) findViewById(R.id.button4);
        en = (Button) findViewById(R.id.button5);
        fn= (Button) findViewById(R.id.button6);
        gn = (Button) findViewById(R.id.button7);
        hn = (Button) findViewById(R.id.button8);
        in = (Button) findViewById(R.id.button9);
        jn = (Button) findViewById(R.id.button10);
        kn = (Button) findViewById(R.id.button11);
        ln = (Button) findViewById(R.id.button12);
        rn = (Button) findViewById(R.id.button13);
        on = (Button) findViewById(R.id.button14);
        un = (Button) findViewById(R.id.button15);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        configurarAdaptadorBluetooth();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fab = new Intent(MainActivity.this, DeviceListActivity.class);
                startActivity(fab);
                if (bAdapter.isEnabled())
                    bAdapter.disable();
                else {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH);
                }
            }
        });

        // EN EL ONCREATE TAMBIEN VAS A DECLARAR LOS LISTENER
        // AJURO, SI NO, VAS A MALGASTAR MEMORIA.
        an.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("h");
                Toast.makeText(getBaseContext(),"openAll",Toast.LENGTH_SHORT).show();
            }
        });
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("g");
                Toast.makeText(getBaseContext(),"closeAll",Toast.LENGTH_SHORT).show();
            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("i");
                Toast.makeText(getBaseContext(),"Sensor",Toast.LENGTH_SHORT).show();
            }
        });
        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("e");
                Toast.makeText(getBaseContext(),"servo 5.Close.Meñique",Toast.LENGTH_SHORT).show();
            }
        });
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("d");
                Toast.makeText(getBaseContext(),"servo 4.Close.Anular",Toast.LENGTH_SHORT).show();
            }
        });
        fn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("c");
                Toast.makeText(getBaseContext(),"servo 3.Close.Cordial",Toast.LENGTH_SHORT).show();
            }
        });
        gn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("b");
                Toast.makeText(getBaseContext(),"servo 2.Close.indice",Toast.LENGTH_SHORT).show();
            }
        });
        hn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("a");
                Toast.makeText(getBaseContext(),"servo 1.Close.pulgar",Toast.LENGTH_SHORT).show();
            }
        });
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("E");
                Toast.makeText(getBaseContext(),"servo 5.Close.Meñique",Toast.LENGTH_SHORT).show();
            }
        });
        jn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("D");
                Toast.makeText(getBaseContext(),"servo 4.Close.Anular",Toast.LENGTH_SHORT).show();
            }
        });
        kn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("C");
                Toast.makeText(getBaseContext(),"servo 3.Close.Cordial",Toast.LENGTH_SHORT).show();
            }
        });
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("B");
                Toast.makeText(getBaseContext(),"servo 2.Close.indice",Toast.LENGTH_SHORT).show();
            }
        });
        rn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("A");
                Toast.makeText(getBaseContext(),"servo 1.Close.pulgar",Toast.LENGTH_SHORT).show();
            }
        });
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("f");
                Toast.makeText(getBaseContext(),"servo 6.TurnLeft.Muñeca",Toast.LENGTH_SHORT).show();
            }
        });
        un.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mConnectedThread.write("F");
                Toast.makeText(getBaseContext(),"servo 6.TurnRight.Muñeca",Toast.LENGTH_SHORT).show();
            }
        });

//        mConnectedThread = new ConnectedThread(btSocket);
//        mConnectedThread.start();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case REQUEST_ENABLE_BLUETOOTH:{
                if (resultCode == RESULT_OK){
                    //acciones que se realizaran si el bluetooth se activa
                } else{
                    Toast.makeText(MainActivity.this,"Conexion fallida, intente nuevamente", Toast.LENGTH_LONG).show();//acciones que se realizaran si el bluetooth no se activa
                    //acciones que se realizaran si el bluetooth no se activa
                }
            }
            default:
                break;
        }
    }

        // metodo onDestroy para eliminar el registro de eventos capturados
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(bReceiver);
    }

    //clase para indicar el thread que se ejecutara en segundo plano
    private class ConnectedThread extends Thread {
        private final OutputStream mmOutStream;

        // ESTO TE VA A LEVANTAR UN NullPointerException
        // PORQUE, TU OBJETO BluetoothSocket NO HA SIDO INSTANCIADO,
        // POR LO TANTO ES NULL.
        ConnectedThread(BluetoothSocket socket) {
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            try {
                tmpOut = socket.getOutputStream();


                // AQUI SE ESTA AGARRANDO EL ERROR DE NullPointerException
                // Y IOException.
                //
                //
                // APRENDE DE LOS ERRORES DE JAVA.
                //
                // NullPointerException: Es llamado cuando un objeto de java es nulo
                // y para evadir ese error, tienes que tener una condicional o un
                // try catch
                //
                // IOException: Es llamado cuando no se puede escribir a un stream
                // o cuando no puedes leer del mismo, entre otros.
                //
            } catch (IOException | NullPointerException ignored) {
                Log.d(TAG, ignored.toString());
            }

            mmOutStream = tmpOut;
        }

        /* Call this from the main activity to send data to the remote device */
        void write(String Imput) {
            byte[] buffer = Imput.getBytes();
            try {
                mmOutStream.write(buffer);
            } catch (IOException ignored) {}
        }

        // PARA QUE TU CODIGO DE HILO CORRA EN BACKGROUND,
        // TIENES QUE IMPLEMENTAR EL METODO RUN.
        @Override
        public void run() {
            super.run();

        }
    }
}
